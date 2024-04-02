package com.adobe.aem.practice.core.models.impl;

import com.adobe.aem.practice.core.models.AemPracticeModel;
import com.adobe.aem.practice.core.models.NavigationItem;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.inject.Named;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.day.cq.commons.jcr.JcrConstants.JCR_TITLE;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = AemPracticeModel.class,
        resourceType = "my-project/components/inner",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AemPracticeModelImpl implements AemPracticeModel {

    @ScriptVariable
    private Page currentPage;

    @ValueMapValue
    @Named(JCR_TITLE)
    private String title;

    @ValueMapValue
    private String imagePath;

    @ValueMapValue
    private boolean backgroundImageEnabled;

    @ChildResource
    @Named("actions")
    private List<NavigationItem> navigationItems;

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getImagePath() {
        return this.imagePath;
    }

    @Override
    public List<NavigationItem> getNavigationItems() {

        return Optional.ofNullable(this.navigationItems)
                .map(items -> items.stream()
                        .map(item -> {
                            if(!item.getText().contains("TEST_")){
                                String modifiedText = "TEST_" + item.getText();
                                item.setText(modifiedText);
                            }
                            return item;
                        })
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    @Override
    public boolean isBackgroundImage() {
        return this.backgroundImageEnabled;
    }

    @Override
    public String getPageNameAndPath(){
        return currentPage.getName().concat(": ").concat(currentPage.getPath());
    }
}
