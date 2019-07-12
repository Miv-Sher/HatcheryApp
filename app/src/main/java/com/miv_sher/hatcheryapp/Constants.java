package com.miv_sher.hatcheryapp;

public class Constants {
    public static final int RARITY_COMMON = 1;
    public static final int RARITY_UNCOMMON = 2;
    public static final int RARITY_RARE = 3;
    public static final int RARITY_LEGENDARY = 4;
    public static final int RARITY_EPIC = 5;

    //на случай, если мы хотим сохранить сессию, но не хотим пока ее стартовать
    public static final String SESSION_PHASE_NOT_STARTED = "session_phase_not_started";
    //таймер тикает
    public static final String SESSION_PHASE_ON_GOING = "session_phase_on_going";
    //юзер отвлекся, сессия не засчитана
    public static final String SESSION_PHASE_FINISHED_FAILED = "session_phase_finished_failed";
    //юзер успешно завершил сессию, но не открыл яйцо
    public static final String SESSION_PHASE_FINISHED_SUCCEED = "session_phase_finished_succeed";
    //юзер преуспел, открыл яйцо, но еще не завершил сессию и смотрит на животину
    public static final String SESSION_PHASE_SHOWING_BEAST = "session_phase_showing_beast";
    //юзер полностью завершил сессию
    public static final String SESSION_PHASE_COMPLETELY_ENDED = "session_phase_completely_ended";


}
