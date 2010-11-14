package at.silverstrike.pcc.impl.schedulingpanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.projectscheduler.ProjectScheduler;
import at.silverstrike.pcc.api.projectscheduler.ProjectSchedulerFactory;
import at.silverstrike.pcc.api.schedulingpanel.SchedulingPanel;

import com.google.inject.Injector;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import eu.livotov.tpt.i18n.TM;

class DefaultSchedulingPanel extends Panel implements SchedulingPanel {
    private static final String NEWLINE = System.getProperty("line.separator");

    private static final long serialVersionUID = 1L;

    private Injector injector;
    private final Logger LOGGER =
        LoggerFactory.getLogger(DefaultSchedulingPanel.class);
    private ProgressIndicator progressIndicator;

    private TextField loggingTextArea;

    public DefaultSchedulingPanel() {
    }

    @Override
    public void initGui() {
        this.setWidth("500px");
        this.setHeight("400px");

        setCaption(TM.get("schedulingpanel.1-caption"));

        final VerticalLayout layout = new VerticalLayout();

        final HorizontalLayout progressAndButtonPanel = new HorizontalLayout();
        progressIndicator = new ProgressIndicator();
        progressIndicator.setIndeterminate(true);
        progressIndicator.setEnabled(false);

        final Button startButton =
                new Button(TM.get("schedulingpanel.2-start-button"));

        startButton.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent event) {
                startButtonClicked();
            }
        });

        progressAndButtonPanel.addComponent(progressIndicator);
        progressAndButtonPanel.addComponent(startButton);

        layout.addComponent(progressAndButtonPanel);

        loggingTextArea = new TextField();
        loggingTextArea.setRows(20);
        loggingTextArea.setColumns(20);

        layout.addComponent(loggingTextArea);

        addComponent(layout);
    }

    @Override
    public void setInjector(final Injector anInjector) {
        injector = anInjector;
    }

    @Override
    public Panel toPanel() {
        return this;
    }

    protected void startButtonClicked() {
        progressIndicator.setEnabled(true);

        final ProjectSchedulerFactory factory =
                injector.getInstance(ProjectSchedulerFactory.class);
        final ProjectScheduler scheduler = factory.create();

        appendToLoggingTextArea("0");
        
        scheduler.setDirectory(System.getProperty("user.dir"));
        scheduler.setInjector(injector);

        try {
            appendToLoggingTextArea("1");
            scheduler.run();
            appendToLoggingTextArea("2");
            progressIndicator.setEnabled(false);
        } catch (final PccException exception) {
            appendToLoggingTextArea("3");
            LOGGER.error("", exception);
            progressIndicator.setEnabled(false);
            appendToLoggingTextArea("4");
        }
    }

    private void appendToLoggingTextArea(final String aStuffToAppend) {
        loggingTextArea.setValue(loggingTextArea.getValue() + NEWLINE
                + aStuffToAppend);
    }
}