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
package at.silverstrike.pcc.api.model;

public enum ProcessState {
    /* Дело занесено в базу данных, но без трудозатрат */
    REPORTED,
    WELL_DEFINED,
    /* Трудозатраты указаны */
    EFFORT_ESTIMATED,
    /* Время достижения дела расчитано */
    SCHEDULED,
    /* Достигается */
    IS_BEING_ATTAINED,
    /* Достижение цели приостановлено */
    PAUSED,
    /* Цель достигнута */
    ATTAINED,
    /* Отказ от цели */
    CANCELLED,
    /* Дело удалено */
    DELETED
}
