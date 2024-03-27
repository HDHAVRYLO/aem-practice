package com.adobe.aem.practice.core.models;

import java.util.List;

public interface AemPracticeModel {
    String getTitle();

    String getImagePath();

    List<NavigationItem> getNavigationItems();

    boolean isBackgroundImage();

    String getPageNameAndPath();
}
