package com.example.k3s2_psk1lab.usecases;

import com.example.k3s2_psk1lab.interceptors.LoggedInvocation;
import com.example.k3s2_psk1lab.services.DoSomethingLong;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;

@SessionScoped
@Named
public class GenerateCompetitionLuckyNumber implements Serializable {

    @Inject
    DoSomethingLong doSomethingLong;

    @Resource
    private ManagedExecutorService executorService;

    private Future<Integer> luckyNumberGenerationTask = null;

    @LoggedInvocation
    public void generateNewLuckyNumber() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        luckyNumberGenerationTask = executorService.submit(() -> doSomethingLong.generateLuckyNumber());

        //return "/competingAthletes.xhtml?competitionId=" + requestParameters.get("competitionId");
    }

    public boolean isLuckyGenerationRunning() {
        return luckyNumberGenerationTask != null && !luckyNumberGenerationTask.isDone();
    }

    public String getLuckyStatus() throws ExecutionException, InterruptedException {
        if (luckyNumberGenerationTask == null) {
            return "No lucky number";
        } else if (isLuckyGenerationRunning()) {
            return "Lucky generation in progress";
        } else {
            Integer suggestedLuckyNumber = luckyNumberGenerationTask.get();
            return "Suggested lucky number: " + (suggestedLuckyNumber != null ? suggestedLuckyNumber : "N/A");
        }
    }

    public String getLuckyGenerationStatus() throws ExecutionException, InterruptedException {
        if (luckyNumberGenerationTask == null) {
            return "No lucky number";
        } else if (isLuckyGenerationRunning()) {
            return "Lucky generation in progress";
        } else {
            Map<String, String> requestParameters =
                    FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

            Integer suggestedLuckyNumber = luckyNumberGenerationTask.get();
//            return "Suggested lucky number: " + (suggestedLuckyNumber != null ? suggestedLuckyNumber : "N/A");
            //action="#{'competingAthletes?faces-redirect=true&amp;competitionId='.concat(competition.id)}">
            return "/competingAthletes.xhtml?faces-redirect=true&status=" + (suggestedLuckyNumber != null ? suggestedLuckyNumber : "N/A") + "&competitionId="+ requestParameters.get("competitionId");
        }
    }

}
