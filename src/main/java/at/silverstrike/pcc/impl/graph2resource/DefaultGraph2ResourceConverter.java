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

package at.silverstrike.pcc.impl.graph2resource;

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

import ru.altruix.commons.api.di.PccException;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import at.silverstrike.pcc.api.graph2resource.Graph2ResourceConverter;
import at.silverstrike.pcc.api.graph2resource.JungResource;
import at.silverstrike.pcc.api.projectnetworkgraphcreator.ProjectNetworkGraph;

/**
 * @author DP118M
 * 
 */
class DefaultGraph2ResourceConverter implements Graph2ResourceConverter {
    private static final double ASSUMED_NODE_WIDTH = 10.;
    private static final double ASSUMED_NODE_HEIGHT = 10.;
    private static final float NINETY_FIVE_PERCENT = 0.95f;
    private static final float FIVE_PERCENT = 0.05f;
    private static final int DEFAULT_HEIGHT_PIXELS = 350;
    private static final int DEFAULT_WIDTH_PIXELS = 600;

    private ProjectNetworkGraph graph;
    private JungResource resource;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultGraph2ResourceConverter.class);
    private String initialEventVertex;
    private String finalEventVertex;

    @Override
    public void run() throws PccException {
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

            this.initialEventVertex = this.graph.getInitialEventVertex();
            this.finalEventVertex = this.graph.getFinalEventVertex();

            final VisualizationImageServer<String, String> server =
                    createServer(this.graph);

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

            this.resource = new DefaultJungResource(bout);
        } catch (final UnsupportedEncodingException exception) {
            LOGGER.error(ErrorCodes.M_002_UNSUPPORTED_ENCONDING, exception);
        } catch (final SVGGraphics2DIOException exception) {
            LOGGER.error(ErrorCodes.M_003_SVG_GRAPHICS_2D_IO, exception);
        } catch (final ParserConfigurationException exception) {
            LOGGER.error(ErrorCodes.M_004_PARSER_CONFIGURATION, exception);
        }

    }

    private VisualizationImageServer<String, String> createServer(
            final Graph<String, String> aGraph) {
        final Layout<String, String> layout = new FRLayout<String, String>(
                aGraph);

        layout.setSize(new Dimension(DEFAULT_WIDTH_PIXELS,
                DEFAULT_HEIGHT_PIXELS));

        lockVertex(this.initialEventVertex, new Point(
                (int) (DEFAULT_WIDTH_PIXELS * FIVE_PERCENT),
                (int) ((DEFAULT_HEIGHT_PIXELS - ASSUMED_NODE_WIDTH) / 2.)),
                layout);
        lockVertex(this.finalEventVertex, new Point(
                (int) (DEFAULT_WIDTH_PIXELS * NINETY_FIVE_PERCENT),
                (int) ((DEFAULT_HEIGHT_PIXELS - ASSUMED_NODE_HEIGHT) / 2.)),
                layout);

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

    @Override
    public void setGraph(final ProjectNetworkGraph aGraph) {
        this.graph = aGraph;
    }

    @Override
    public JungResource getResource() {
        return resource;
    }
}
