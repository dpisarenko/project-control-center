/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010, 2011 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.impl.graphdemopanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.functors.ConstantTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Embedded;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.RadialTreeLayout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.DelegateTree;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.graph.Tree;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import eu.livotov.tpt.TPTApplication;

/**
 * @author DP118M
 * 
 */
class L2RTreeLayoutDemo {
    private static final int DEFAULT_HEIGHT_PIXELS = 350;
    private static final int DEFAULT_WIDTH_PIXELS = 350;

    private Embedded imageComponent;
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(L2RTreeLayoutDemo.class);

    /**
     * the graph
     */
    Forest<String, Integer> graph;

    Factory<DirectedGraph<String, Integer>> graphFactory =
            new Factory<DirectedGraph<String, Integer>>() {

                public DirectedGraph<String, Integer> create() {
                    return new DirectedSparseMultigraph<String, Integer>();
                }
            };

    Factory<Tree<String, Integer>> treeFactory =
                new Factory<Tree<String, Integer>>() {

                    public Tree<String, Integer> create() {
                        return new DelegateTree<String, Integer>(graphFactory);
                    }
                };

    Factory<Integer> edgeFactory = new Factory<Integer>() {
        int i = 0;

        public Integer create() {
            return i++;
        }
    };

    Factory<String> vertexFactory = new Factory<String>() {
        int i = 0;

        public String create() {
            return "V" + i++;
        }
    };

    /**
     * the visual component and renderer for the graph
     */
    VisualizationViewer<String, Integer> vv;
    
    String root;

    TreeLayout<String, Integer> treeLayout;

//    RadialTreeLayout<String, Integer> radialLayout;

    private void setLtoR(VisualizationViewer<String, Integer> vv) {
        Layout<String, Integer> layout = vv.getModel().getGraphLayout();
        Dimension d = layout.getSize();
        Point2D center = new Point2D.Double(d.width / 2, d.height / 2);
        vv.getRenderContext().getMultiLayerTransformer()
                .getTransformer(Layer.LAYOUT).rotate(-Math.PI / 2, center);
    }

    /**
     * 
     */
    private void createTree() {
//        graph.addVertex("V0");
//        graph.addEdge(edgeFactory.create(), "V0", "V1");
//        graph.addEdge(edgeFactory.create(), "V0", "V2");
//        graph.addEdge(edgeFactory.create(), "V1", "V4");
//        graph.addEdge(edgeFactory.create(), "V2", "V3");
//        graph.addEdge(edgeFactory.create(), "V2", "V5");
//        graph.addEdge(edgeFactory.create(), "V4", "V6");
//        graph.addEdge(edgeFactory.create(), "V4", "V7");
//        graph.addEdge(edgeFactory.create(), "V3", "V8");
//        graph.addEdge(edgeFactory.create(), "V6", "V9");
//        graph.addEdge(edgeFactory.create(), "V4", "V10");

        graph.addVertex("A0");
        graph.addEdge(edgeFactory.create(), "A0", "A1");
        graph.addEdge(edgeFactory.create(), "A0", "A2");
        graph.addEdge(edgeFactory.create(), "A0", "A3");

//        graph.addVertex("B0");
//        graph.addEdge(edgeFactory.create(), "B0", "B1");
//        graph.addEdge(edgeFactory.create(), "B0", "B2");
//        graph.addEdge(edgeFactory.create(), "B1", "B4");
//        graph.addEdge(edgeFactory.create(), "B2", "B3");
//        graph.addEdge(edgeFactory.create(), "B2", "B5");
//        graph.addEdge(edgeFactory.create(), "B4", "B6");
//        graph.addEdge(edgeFactory.create(), "B4", "B7");
//        graph.addEdge(edgeFactory.create(), "B3", "B8");
//        graph.addEdge(edgeFactory.create(), "B6", "B9");
    }

    void run() {

        // create a simple graph for the demo
        graph = new DelegateForest<String, Integer>();

        createTree();

        treeLayout = new TreeLayout<String, Integer>(graph);
        vv =
                new VisualizationViewer<String, Integer>(treeLayout,
                        new Dimension(600, 600));
        vv.setBackground(Color.white);
        vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line());
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        // add a listener for ToolTips
        vv.setVertexToolTipTransformer(new ToStringLabeller());
        vv.getRenderContext().setArrowFillPaintTransformer(
                new ConstantTransformer(Color.lightGray));

        setLtoR(vv);

        createImage();

    }

    private void createImage() {
        try {
            final DocumentBuilderFactory docBuilderFactory =
                    DocumentBuilderFactory
                            .newInstance();
            final DocumentBuilder docBuilder =
                    docBuilderFactory.newDocumentBuilder();
            final Document document = docBuilder.newDocument();
            final Element svgelem = document.createElement("svg");
            document.appendChild(svgelem);

            final SVGGraphics2D graphic2d = new SVGGraphics2D(document);

            final VisualizationImageServer<String, Integer> server =
                    createServer(graph);

            server.printAll(graphic2d);

            final Element el = graphic2d.getRoot();
            el.setAttributeNS(null, "viewBox", "0 0 350 350");
            el.setAttributeNS(null, "style", "width:100%;height:100%;");

            final ByteArrayOutputStream bout = new ByteArrayOutputStream();

            final Writer out = new OutputStreamWriter(bout, "UTF-8");
            graphic2d.stream(el, out);

            final JungResource source = new JungResource(bout);

            TPTApplication.getCurrentApplication().addResource(source);

            imageComponent = new Embedded("", source);

            imageComponent.setWidth(DEFAULT_WIDTH_PIXELS, Sizeable.UNITS_PIXELS);
            imageComponent.setHeight(DEFAULT_HEIGHT_PIXELS, Sizeable.UNITS_PIXELS);
            imageComponent.setMimeType("image/svg+xml");
        } catch (final UnsupportedEncodingException exception) {
            LOGGER.error(ErrorCodes.M_001_UNSUPPORTED_ENCONDING, exception);
        } catch (final SVGGraphics2DIOException exception) {
            LOGGER.error(ErrorCodes.M_002_SVG_GRAPHICS_2D_IO, exception);
        } catch (final ParserConfigurationException exception) {
            LOGGER.error(ErrorCodes.M_003_PARSER_CONFIGURATION, exception);
        }
    }

    public Embedded getImage() {
        return imageComponent;
    }

    private VisualizationImageServer<String, Integer> createServer(
            final Forest<String, Integer> aGraph) {
        final VisualizationImageServer<String, Integer> vv =
                new VisualizationImageServer<String, Integer>(
                        this.treeLayout, new Dimension(350, 350));
        vv.getRenderContext().setVertexLabelTransformer(
                new ToStringLabeller<String>());
        return vv;
    }

}
