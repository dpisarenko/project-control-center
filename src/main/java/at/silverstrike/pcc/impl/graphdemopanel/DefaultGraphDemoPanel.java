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
import java.awt.Point;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import eu.livotov.tpt.TPTApplication;

import com.vaadin.ui.Embedded;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.graphdemopanel.GraphDemoPanel;

class DefaultGraphDemoPanel extends Panel implements GraphDemoPanel {
    private static final int DEFAULT_HEIGHT_PIXELS = 350;
    private static final int DEFAULT_WIDTH_PIXELS = 600;
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultGraphDemoPanel.class);
    private String initialEventVertex;
    private String finalEventVertex;

    @Override
    public Panel toPanel() {
        return this;
    }

    @Override
    public void initGui() {
        final VerticalLayout layout = new VerticalLayout();

        // final L2RTreeLayoutDemo l2rTree = new L2RTreeLayoutDemo();
        //
        // l2rTree.run();
        // final Embedded image = l2rTree.getImage();

        final Embedded image = createSampleGraph();
        layout.addComponent(image);
        layout.setSizeFull();

        this.addComponent(layout);

        JGraphDemo jgraphStuff = new JGraphDemo();
        try {
            jgraphStuff.run();
        } catch (PccException exception) {
            LOGGER.error("", exception);
        }
    }

    private Embedded createSampleGraph() {
        Embedded imageComponent = null;

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

            final Graph<String, String> graph = createGraph();
            final VisualizationImageServer<String, String> server =
                    createServer(graph);

            server.printAll(graphic2d);

            final Element el = graphic2d.getRoot();
            el.setAttributeNS(
                    null,
                    "viewBox",
                    "0 0 ${width} ${height}".replace("${width}",
                            String.valueOf(DEFAULT_WIDTH_PIXELS))
                            .replace("${height}",
                                    String.valueOf(DEFAULT_HEIGHT_PIXELS)));
            el.setAttributeNS(null, "style", "width:100%;height:100%;");

            final ByteArrayOutputStream bout = new ByteArrayOutputStream();

            final Writer out = new OutputStreamWriter(bout, "UTF-8");
            graphic2d.stream(el, out);

            final JungResource source = new JungResource(bout);

            TPTApplication.getCurrentApplication().addResource(source);

            imageComponent = new Embedded("", source);

            imageComponent.setWidth(DEFAULT_WIDTH_PIXELS, UNITS_PIXELS);
            imageComponent.setHeight(DEFAULT_HEIGHT_PIXELS, UNITS_PIXELS);
            imageComponent.setMimeType("image/svg+xml");
            addComponent(imageComponent);
        } catch (final UnsupportedEncodingException exception) {
            LOGGER.error(ErrorCodes.M_001_UNSUPPORTED_ENCONDING, exception);
        } catch (final SVGGraphics2DIOException exception) {
            LOGGER.error(ErrorCodes.M_002_SVG_GRAPHICS_2D_IO, exception);
        } catch (final ParserConfigurationException exception) {
            LOGGER.error(ErrorCodes.M_003_PARSER_CONFIGURATION, exception);
        }
        return imageComponent;
    }

    private VisualizationImageServer<String, String> createServer(
            final Graph<String, String> aGraph) {
        final Layout<String, String> layout = new FRLayout<String, String>(
                aGraph);

        layout.setSize(new Dimension(DEFAULT_WIDTH_PIXELS,
                DEFAULT_HEIGHT_PIXELS));

        lockVertex(this.initialEventVertex, new Point(
                (int) (DEFAULT_WIDTH_PIXELS * 0.05f),
                (int) ((DEFAULT_HEIGHT_PIXELS - 10.) / 2.)), layout);
        lockVertex(this.finalEventVertex, new Point(
                (int) (DEFAULT_WIDTH_PIXELS * 0.95f),
                (int) ((DEFAULT_HEIGHT_PIXELS - 10.) / 2.)), layout);

        final VisualizationImageServer<String, String> vv =
                new VisualizationImageServer<String, String>(
                        layout, new Dimension(DEFAULT_WIDTH_PIXELS,
                                DEFAULT_HEIGHT_PIXELS));
        vv.getRenderContext().setVertexLabelTransformer(
                new ToStringLabeller<String>());
        vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line());
        
        return vv;
    }

    private void lockVertex(final String aVertex, final Point2D aLocation,
            final Layout<String, String> aLayout) {
        aLayout.setLocation(aVertex, aLocation);
        aLayout.lock(aVertex, true);
    }

    private Graph<String, String> createGraph() {
        final Graph<String, String> graph =
                new DirectedSparseMultigraph<String, String>();
        initialEventVertex = "IE";
        final String vertex2 = "P1";
        final String vertex3 = "P2";
        final String vertex4 = "P3";
        finalEventVertex = "FE";

        graph.addVertex(initialEventVertex);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);
        graph.addVertex(finalEventVertex);

        graph.addEdge("1", initialEventVertex, vertex2, EdgeType.DIRECTED);
        graph.addEdge("2", vertex2, vertex3, EdgeType.DIRECTED);
        graph.addEdge("3", vertex3, finalEventVertex, EdgeType.DIRECTED);
        graph.addEdge("4", initialEventVertex, vertex4, EdgeType.DIRECTED);
        graph.addEdge("5", vertex4, finalEventVertex, EdgeType.DIRECTED);
        return graph;
    }

}
