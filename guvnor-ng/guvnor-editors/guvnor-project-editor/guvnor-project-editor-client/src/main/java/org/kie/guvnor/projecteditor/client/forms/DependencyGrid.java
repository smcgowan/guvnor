/*
 * Copyright 2013 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.guvnor.projecteditor.client.forms;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import org.kie.guvnor.project.model.Dependency;
import org.kie.guvnor.project.model.GroupArtifactVersionModel;

import javax.inject.Inject;
import java.util.List;

public class DependencyGrid
        implements IsWidget, DependencyGridView.Presenter {

    private final DependencyGridView view;
    private List<Dependency> dependencies;
    private final DependencySelectorPopup dependencySelectorPopup;

    @Inject
    public DependencyGrid(DependencySelectorPopup dependencySelectorPopup,
                          DependencyGridView view) {
        this.dependencySelectorPopup = dependencySelectorPopup;
        dependencySelectorPopup.addSelectionHandler(new SelectionHandler<GroupArtifactVersionModel>() {
            @Override
            public void onSelection(SelectionEvent<GroupArtifactVersionModel> event) {
                //TODO -Rikkola-
            }
        });
        this.view = view;
        view.setPresenter(this);
    }

    public void fillList(List<Dependency> dependencies) {
        this.dependencies = dependencies;
        view.setList(dependencies);
    }

    @Override
    public Widget asWidget() {
        return view.asWidget();
    }

    @Override
    public void onAddDependencyButton() {
        dependencies.add(new Dependency());
        fillList(dependencies);
    }

    @Override
    public void onAddDependencyFromRepositoryButton() {
        dependencySelectorPopup.show();
    }

}
