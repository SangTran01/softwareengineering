package com.neonatal.app.src.classes;

import com.jjoe64.graphview.GraphView;
import com.neonatal.app.src.GraphFullScreenActivity;

public abstract class BaseExample {
    public abstract void onCreate(GraphFullScreenActivity fullscreenActivity);
    public abstract void initGraph(GraphView graph);

    public void onPause() {}
    public void onResume() {}
}
