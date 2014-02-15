
package cz.brejla.pjj.web.graph.presenters;

import cz.brejla.java.web.formelo.application.FormeloApplication;

/**
 *
 * @author warden
 */
public class ViewerPresenter extends BasePresenter {


    public GraphViewer createComponentGraphViewer() {
        GraphViewer gw = new GraphViewer(Integer.parseInt(FormeloApplication.getSession().getAttribute("width").toString()),
                Integer.parseInt(FormeloApplication.getSession().getAttribute("height").toString()));

        return gw;
    }

    public void actionShow() {
        GraphViewer gw = (GraphViewer) getComponent("GraphViewer");
        gw.generate();
        terminate();
    }

}
