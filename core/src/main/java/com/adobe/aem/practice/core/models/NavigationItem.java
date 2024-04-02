package com.adobe.aem.practice.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NavigationItem {

    @ValueMapValue
    private String link;

    @ValueMapValue
    private String linkTarget;

    @ValueMapValue
    private String text;

    public String getLink() {
        return this.link;
    }

    public String getLinkTarget(){
        return this.linkTarget;
    }

    public String getText(){
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
