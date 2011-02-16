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

package at.silverstrike.pcc.impl.export2tj3;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.embeddedfilereading.EmbeddedFileReader;
import at.silverstrike.pcc.api.export2tj3.InvalidDurationException;
import at.silverstrike.pcc.api.export2tj3.NoProcessesException;
import at.silverstrike.pcc.api.export2tj3.NoProjectExportInfoException;
import at.silverstrike.pcc.api.export2tj3.NoResourcesException;
import at.silverstrike.pcc.api.export2tj3.TaskJuggler3Exporter;
import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.DailyLimitResourceAllocation;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.ResourceAllocation;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.projectscheduler.ProjectExportInfo;

import com.google.inject.Injector;

/**
 * @author Dmitri Pisarenko
 * 
 */
class DefaultTaskJuggler3Exporter implements TaskJuggler3Exporter {
	private static final String RESOURCE_ID = "${id}";

	private static final String ABBREVIATION = "${abbreviation}";

	private static final String CHILD_TASKS = "${childTasks}";

	private static final String DAILY_LIMIT_IN_HOURS = "${dailyLimitInHours}";

	private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");

	private static final String EFFORT = "${effort}";

	private static final String EFFORT_INFO = "${effortInfo}";

	private static final String EXPORT2TJ3_TEMPLATE_EFFORT = "export2tj3.template.effort";

	private static final String EXPORT2TJ3_TEMPLATE_RESOURCEALLOCATION_LIMITS = "export2tj3.template.resourceallocation.limits";

	private static final String EXPORT2TJ3_TEMPLATE_RESOURCEALLOCATION_NOLIMITS = "export2tj3.template.resourceallocation.nolimits";

	private static final String EXPORT2TJ3_TEMPLATE_START = "export2tj3.template.start";

	private static final String EXPORT2TJ3_TEMPLATE_TASK = "export2tj3.template.task";

	private static final String ID = "${id}";

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DefaultTaskJuggler3Exporter.class);

	private static final String MAX_HOURS_PER_DAY = "${maxHoursPerDay}";

	private static final int MAX_TASK_NAME_LENGTH = 30;

	private static final String NAME = "${name}";

	private static DecimalFormat NUMBER_FORMAT = new DecimalFormat("#0.00");

	private static final String PLACEHOLDER_COPYRIGHT = "${copyright}";

	private static final String PLACEHOLDER_CURRENCY = "${currency}";

	private static final String PLACEHOLDER_NOW = "${now}";

	private static final String PLACEHOLDER_PROJECT_NAME = "${projectName}";

	private static final String PLACEHOLDER_SCHEDULING_HORIZON_MONTHS = "${schedulingHorizonMonths}";

	private static final String PRIORITY = "${priority}";

	private static final String PROJECT_HEADER_TEMPLATE = "export2tj3.template.project";

	private static final String REPORT_TEMPLATE = "export2tj3.template.reports";

	private static final String RESOURCE = "${resource}";

	private static final String RESOURCE_ALLOCATIONS = "${resourceAllocations}";

	private static final String RESOURCE_TEMPLATE = "export2tj3.template.resource";
	private static final String START_DATE_TIME = "${startDateTime}";

	private static final Double TIMING_RESOLUTION_IN_HOURS = 0.25;

	private String effortTemplate;
	private EmbeddedFileReader embeddedFileReader;
	private Persistence persistence;
	private String resourceAllocationLimitsTemplate;
	private String resourceAllocationNoLimitsTemplate;
	private String result;
	private String startDateTimeTemplate;
	private String taskTemplate;
	private ProjectExportInfo projectExportInfo;

	public DefaultTaskJuggler3Exporter() {
	}

	@Override
	public String getTaskJugglerIIIProjectFileContents() {
		return result;
	}

	/**
	 * @see at.silverstrike.pcc.api.export2tj3.TaskJuggler3Exporter#run()
	 */
	@Override
	public void run() throws NoProcessesException, NoResourcesException,
			PccException, InvalidDurationException {
		validateInputs();

		final StringBuilder builder = new StringBuilder();

		LOGGER.debug("this.embeddedFileReader: " + embeddedFileReader);

		final String projectTemplate = embeddedFileReader
				.readEmbeddedFile(PROJECT_HEADER_TEMPLATE);
		final String projectHeader = substituteProjectHeaderPlaceholders(projectTemplate);

		builder.append(projectHeader);

		// Add resource information
		addResourceInformation(builder);

		// Add task information

		final List<ControlProcess> processes = this.projectExportInfo
				.getControlProcessesToExport();
		if (processes != null) {
			taskTemplate = embeddedFileReader
					.readEmbeddedFile(EXPORT2TJ3_TEMPLATE_TASK);
			effortTemplate = embeddedFileReader
					.readEmbeddedFile(EXPORT2TJ3_TEMPLATE_EFFORT);
			resourceAllocationLimitsTemplate = embeddedFileReader
					.readEmbeddedFile(EXPORT2TJ3_TEMPLATE_RESOURCEALLOCATION_LIMITS);
			resourceAllocationNoLimitsTemplate = embeddedFileReader
					.readEmbeddedFile(EXPORT2TJ3_TEMPLATE_RESOURCEALLOCATION_NOLIMITS);
			startDateTimeTemplate = embeddedFileReader
					.readEmbeddedFile(EXPORT2TJ3_TEMPLATE_START);

			for (final ControlProcess process : processes) {
				builder.append(getTaskInformation(process, null));
			}
		}

		// Add footer (report definitions)
		builder.append(embeddedFileReader.readEmbeddedFile(REPORT_TEMPLATE));

		result = builder.toString();
	}

	@Override
	public void setInjector(final Injector anInjector) {
		if (anInjector != null) {
			persistence = anInjector.getInstance(Persistence.class);
			embeddedFileReader = anInjector
					.getInstance(EmbeddedFileReader.class);
		}
	}

	private void addResourceInformation(final StringBuilder builder)
			throws PccException {
		final List<Resource> resources = this.projectExportInfo
				.getResourcesToExport();

		if (resources != null) {
			final String resourceDefinitionTemplate = embeddedFileReader
					.readEmbeddedFile(RESOURCE_TEMPLATE);

			for (final Resource resource : resources) {
				final String resourceDefinition = subsituteResourcePlaceHolders(
						resourceDefinitionTemplate, resource);

				builder.append(resourceDefinition);
			}
		}
	}

	/**
	 * Formats a Date instance to format YYYY-MM-DD, e. g. 2010-04-28 for
	 * 28.04.2010.
	 */
	private CharSequence formatDate(final Date aDate) {
		return DATE_FORMAT.format(aDate);
	}

	private String formatDouble(final double aNumber) {
		return NUMBER_FORMAT.format(aNumber).replace(',', '.');
	}

	private CharSequence formatInt(final int aNumber) {
		return "" + aNumber;
	}

	private CharSequence formatLong(final Long aNumber) {
		if (aNumber != null) {
			return aNumber.toString();
		} else {
			return "";
		}
	}

	private List<ControlProcess> getChildProcesses(final ControlProcess process) {
		return persistence.getChildTasks(process);
	}

	private CharSequence getEffortAllocations(final ControlProcess process) {
		final StringBuilder stringBuilder = new StringBuilder();

		if ((process != null) && (process.getResourceAllocations() != null)) {
			for (final ResourceAllocation resourceAllocation : process
					.getResourceAllocations()) {
				final String resourceAllocationInfo;

				if (resourceAllocation instanceof DailyLimitResourceAllocation) {
					final DailyLimitResourceAllocation limitAlloc = (DailyLimitResourceAllocation) resourceAllocation;

					resourceAllocationInfo = resourceAllocationLimitsTemplate
							.replace(
									RESOURCE,
									getResourceIdentifier(limitAlloc
											.getResource())).replace(
									MAX_HOURS_PER_DAY,
									formatDouble(limitAlloc.getDailyLimit()));
				} else {
					resourceAllocationInfo = resourceAllocationNoLimitsTemplate
							.replace(RESOURCE,
									getResourceIdentifier(resourceAllocation
											.getResource()));
				}
				stringBuilder.append(resourceAllocationInfo);
			}
		}

		return stringBuilder.toString();
	}

	private CharSequence getEffortInfo(final ControlProcess process) {
		final Double bestCaseEffort = process.getBestCaseEffort();
		final Double worstCaseEffort = process.getWorstCaseEffort();

		if ((bestCaseEffort != null) && (worstCaseEffort != null)) {
			return effortTemplate.replace(EFFORT,
					formatDouble(process.getAverageCaseEffort()));
		} else if ((bestCaseEffort != null) && (worstCaseEffort == null)) {
			return effortTemplate.replace(EFFORT, formatDouble(bestCaseEffort));
		} else if ((bestCaseEffort == null) && (worstCaseEffort != null)) {
			return effortTemplate
					.replace(EFFORT, formatDouble(worstCaseEffort));
		} else {
			return effortTemplate.replace(EFFORT, formatDouble(0.));
		}
	}

	private String getResourceIdentifier(final Resource aResource) {
		return "R" + aResource.getId();
	}

	private CharSequence getStartDateTime(final ControlProcess parent) {
		if (parent == null) {
			return startDateTimeTemplate;
		} else {
			return "";
		}
	}

	private String getTaskInformation(final ControlProcess process,
			final ControlProcess parent) {
		final StringBuilder stringBuilder = new StringBuilder();
		final List<ControlProcess> childProcesses = getChildProcesses(process);
		if (childProcesses != null) {
			for (final ControlProcess childProcess : childProcesses) {
				stringBuilder.append(getTaskInformation(childProcess, process));
			}
		}

		final String childProcessDefinitions = stringBuilder.toString();

		final Integer boxedPriority = process.getPriority();
		final int priority;

		if (boxedPriority != null) {
			priority = boxedPriority;
		} else {
			priority = 0;
		}

		final String taskDefinition = taskTemplate
				.replace(ID, formatLong(process.getId()))
				.replace(NAME, shortenName(process.getName()))
				.replace(START_DATE_TIME, getStartDateTime(parent))
				.replace(PRIORITY, formatInt(priority))
				.replace(RESOURCE_ALLOCATIONS, getEffortAllocations(process))
				.replace(EFFORT_INFO, getEffortInfo(process))
				.replace(CHILD_TASKS, childProcessDefinitions);

		return taskDefinition;
	}

	private CharSequence shortenName(final String name) {
		if (name != null) {
			return StringUtils.abbreviate(name, MAX_TASK_NAME_LENGTH);
		} else {
			return "";
		}
	}

	private String subsituteResourcePlaceHolders(
			final String resourceDefinitionTemplate, final Resource resource) {
		final String resourceDefinition = resourceDefinitionTemplate
				.replace(ABBREVIATION, resource.getAbbreviation())
				.replace(RESOURCE_ID, resource.getId().toString())
				.replace(DAILY_LIMIT_IN_HOURS,
						formatDouble(resource.getDailyLimitInHours()));
		return resourceDefinition;
	}

	private String substituteProjectHeaderPlaceholders(
			final String projectTemplate) {

		LOGGER.error("projectTemplate: {}", projectTemplate);
		LOGGER.error("this.projectExportInfo: {}", this.projectExportInfo);

		final String projectHeader = projectTemplate
				.replace(PLACEHOLDER_PROJECT_NAME,
						this.projectExportInfo.getProjectName())
				.replace(PLACEHOLDER_COPYRIGHT,
						this.projectExportInfo.getCopyright())
				.replace(PLACEHOLDER_CURRENCY,
						this.projectExportInfo.getCurrency())
				.replace(PLACEHOLDER_NOW,
						formatDate(this.projectExportInfo.getNow()))
				.replace(
						PLACEHOLDER_SCHEDULING_HORIZON_MONTHS,
						formatInt(this.projectExportInfo
								.getSchedulingHorizonMonths()));
		return projectHeader;
	}

	private void validateInputs() throws NoProcessesException,
			NoResourcesException, NoProjectExportInfoException,
			InvalidDurationException {
		if (this.projectExportInfo == null) {
			throw new NoProjectExportInfoException();
		}

		final List<ControlProcess> processes = this.projectExportInfo
				.getControlProcessesToExport();
		final List<Resource> resources = this.projectExportInfo
				.getResourcesToExport();

		if (processes == null) {
			throw new NoProcessesException();
		}

		if (processes.size() < 1) {
			throw new NoProcessesException();
		}

		if (resources == null) {
			throw new NoResourcesException();
		}

		if (resources.size() < 1) {
			throw new NoResourcesException();
		}

		for (final ControlProcess proc : processes) {
			checkTimingResolution(proc, proc.getBestCaseEffort());
			checkTimingResolution(proc, proc.getAverageCaseEffort());
			checkTimingResolution(proc, proc.getWorstCaseEffort());
		}
	}

	private void checkTimingResolution(ControlProcess proc,
			Double bestCaseEffort) throws InvalidDurationException {
		if ((bestCaseEffort != null)
				&& (bestCaseEffort < TIMING_RESOLUTION_IN_HOURS)) {
			throw new InvalidDurationException(proc.getId(), proc.getName());
		}
	}

	public ProjectExportInfo getProjectExportInfo() {
		return projectExportInfo;
	}

	public void setProjectExportInfo(final ProjectExportInfo projectExportInfo) {
		this.projectExportInfo = projectExportInfo;
	}

}
