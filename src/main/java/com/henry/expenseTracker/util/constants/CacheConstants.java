package com.henry.expenseTracker.util.constants;

public class CacheConstants {
    public static final String EXPENSE_CACHE_NAME = "EXPENSES"; //cache name for expenses
    public static final String CATEGORY_CACHE_NAME = "CATEGORY"; // cache name for categories
    public static final String SUPPLIER_CACHE_NAME = "SUPPLIERS"; //cache name for suppliers
    public static final String EXPIRATIONS_CACHE_NAME = "EXPIRATIONS"; // cache name for expirations
    public static final String SCHEDULED_RESET_CACHE = "0 0 0 * * ?"; //cron expresion every day at 12AM
}
