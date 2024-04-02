package com.adobe.aem.practice.core.models.impl;

import com.adobe.aem.practice.core.models.AemPracticeModel;
import io.wcm.testing.mock.aem.junit.AemContext;
import junit.framework.TestCase;
import org.apache.sling.models.factory.ModelFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AemPracticeModelImplTest extends TestCase {
    private static final String PATH_TO_JSON = "/adobe/aem/practice/core/models/AemPracticeModel.json";
    private static final String PAGE_PATH = "/content/us/en/page";
    private static final String ROOT_PATH = "/content";
    private final static String AUTHORED_COMPONENT_PATH = "/content/aem-practice";

    @Rule
    public final AemContext context = new AemContext();

    private AemPracticeModel model;

    @Before
    public void setup() throws Exception {
        context.addModelsForClasses(AemPracticeModel.class);
        context.load().json(PATH_TO_JSON, ROOT_PATH);
        context.currentPage(PAGE_PATH);
        context.currentResource(AUTHORED_COMPONENT_PATH);
        model = context.getService(ModelFactory.class).createModel(context.request(), AemPracticeModelImpl.class);
    }

    @Test
    public void testGetMessage() throws Exception {
        assertNotNull(model);
        assertEquals("AEM practice", model.getTitle());
        assertEquals("/content/dam/my-project", model.getImagePath());
        assertEquals("page: /content/us/en/page", model.getPageNameAndPath());
        assertTrue(model.isBackgroundImage());
        assertEquals(1, model.getNavigationItems().size());
        assertEquals("/content/h-d/us/en/index", model.getNavigationItems().get(0).getLink());
        assertEquals("TEST_cta", model.getNavigationItems().get(0).getText());
    }
}