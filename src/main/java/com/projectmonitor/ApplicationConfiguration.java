package com.projectmonitor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties
public class ApplicationConfiguration {

    private String ciUrl;
    private String pivotalTrackerStoryDetailsUrl;
    private String storyAcceptanceUrl;
    private String productionUrl;
    private String githubUsername;
    private String githubProjectURL;


    public String getCiUrl() {
        return ciUrl;
    }

    public void setCiUrl(String ciUrl) {
        this.ciUrl = ciUrl;
    }

    public String getPivotalTrackerStoryDetailsUrl() {
        return pivotalTrackerStoryDetailsUrl;
    }

    public void setPivotalTrackerStoryDetailsUrl(String pivotalTrackerStoryDetailsUrl) {
        this.pivotalTrackerStoryDetailsUrl = pivotalTrackerStoryDetailsUrl;
    }

    public String getStoryAcceptanceUrl() {
        return storyAcceptanceUrl;
    }

    public void setStoryAcceptanceUrl(String storyAcceptanceUrl) {
        this.storyAcceptanceUrl = storyAcceptanceUrl;
    }

    public String getProductionUrl() {
        return productionUrl;
    }

    public void setProductionUrl(String productionUrl) {
        this.productionUrl = productionUrl;
    }

    public String getGithubUsername() {
        return githubUsername;
    }

    public void setGithubUsername(String githubUsername) {
        this.githubUsername = githubUsername;
    }

    public String getGithubProjectURL() {
        return githubProjectURL;
    }

    public void setGithubProjectURL(String githubProjectURL) {
        this.githubProjectURL = githubProjectURL;
    }
}
