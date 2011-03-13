/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.impl.graphdemopanel;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.apache.batik.svggen.SVGGraphics2D;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.VisualizationServer;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import at.silverstrike.pcc.api.graphdemopanel.GraphDemoPanel;

class DefaultGraphDemoPanel extends Panel implements GraphDemoPanel {
    private static final long serialVersionUID = 1L;

    @Override
    public Panel toPanel() {
        return this;
    }

    @Override
    public void initGui() {
        final VerticalLayout layout = new VerticalLayout();
        final Embedded image =
                new Embedded(null, new ThemeResource("../pcc/test/graph.gif"));

        layout.addComponent(image);
        layout.setSizeFull();

        this.addComponent(layout);
    }

    private void createSampleGraph() {
        final Graph<String, String> graph = new SparseMultigraph<String, String>();
        final String vertex1 = "IE";
        final String vertex2 = "P1";
        final String vertex3 = "P2";
        final String vertex4 = "P3";
        final String vertex5 = "FE";
        
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);
        graph.addVertex(vertex5);
        
        graph.addEdge("", vertex1, vertex2, EdgeType.DIRECTED);
        graph.addEdge("", vertex2, vertex3, EdgeType.DIRECTED);
        graph.addEdge("", vertex3, vertex5, EdgeType.DIRECTED);
        graph.addEdge("", vertex1, vertex4, EdgeType.DIRECTED);
        graph.addEdge("", vertex4, vertex5, EdgeType.DIRECTED);
        
        final Layout<String, String> layout = new CircleLayout<String, String>(graph);
        layout.setSize(new Dimension(300,300));
        
        
        final VisualizationImageServer<String,String> vv =
            new VisualizationImageServer<String,String>(layout, new Dimension(350,350));

        
        
        SVGGraphics2D a = new SVGGraphics2D(arg0);
        
//        vv.printAll(graphic2d);

        
        
        // ----
        
        
        final VisualizationImageServer<String,String> vv =
            new VisualizationImageServer<String,String>(layout, new Dimension(350,350));
                
        
        BufferedImage bim = new BufferedImage(350, 350, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bim.createGraphics();
        viewFrame.paintAll(g);

        
        
        vv.createImage(bim);
        
        g.dispose();
        //this.viewFrame.paintComponents(g);
        //try{Thread.sleep(1000);} catch(Exception e) {throw new RuntimeException(e);} // Sleeping doesn't help.
        try {
            File f = new File(filename);
            ImageIO.write(bim,"png",f);
            System.out.println("wrote image for " + jungGraph + " to "+ filename+ ":" + f.toString());
            //try{Thread.sleep(500);} catch(Exception e) {throw new RuntimeException(e);} // Doesn't help
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
