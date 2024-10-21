package com.company.jmixbpmtraining.view.processdefinitionlist;

import com.company.jmixbpmtraining.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.bpm.entity.ProcessDefinitionData;
import io.jmix.bpmflowui.view.processdefinition.ProcessDefinitionListView;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewComponent;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "bpm/processdefinitions", layout = MainView.class)
@ViewController("bpm_ProcessDefinition.list")
@ViewDescriptor("jbt-process-definition-list-view.xml")
public class JbtProcessDefinitionListView extends ProcessDefinitionListView {

    @Autowired
    private RepositoryService repositoryService;
    @ViewComponent
    private JmixButton deleteAllVersionsBtn;

    @Subscribe(id = "deleteAllVersionsBtn", subject = "clickListener")
    public void onDeleteAllVersionsBtnClick(final ClickEvent<JmixButton> event) {
        if (processDefinitionsGrid.getSingleSelectedItem() == null) return;

        ProcessDefinitionData selectedItem = processDefinitionsGrid.getSingleSelectedItem();
        String key = selectedItem.getKey();

        List<ProcessDefinition> definitions = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(key)
                .list();

        for (ProcessDefinition definition : definitions) {
            String deploymentId = definition.getDeploymentId();
            repositoryService.deleteDeployment(deploymentId, true); // true to cascade delete
            System.out.println("Deleted process definition: " + key + " ver. " + definition.getVersion());
        }
    }
}