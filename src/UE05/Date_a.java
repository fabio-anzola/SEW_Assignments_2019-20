package UE05;

public class Date_a {
    static final String[] MONTH_NAMES = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private int year;
    private int month;
    private int day;

    /**
     * Initial constructor for Date
     */
    public Date_a() {
        day = 1;
        month = 1;
        year = 1900;
    }

    /**
     * Constructor for a date with a specific date
     *
     * @param monthTmp int
     * @param dayTmp   int
     * @param yearTmp  int
     */
    public Date_a(int monthTmp, int dayTmp, int yearTmp) {
        setDate(monthTmp, dayTmp, yearTmp);
        /*
        boolean invalid = false;
        year = yearTmp;
        if (monthTmp < 13 && monthTmp > 0) {
            month = monthTmp;
        } else {
            invalid = true;
        }
        if (!invalid) {
            if (dayTmp > lastDayInMonth(month) || dayTmp < 1) {
                invalid = true;
            } else {
                day = dayTmp;
            }
        }
        if (invalid) {
            invalidDate();
        }
         */
    }

    /**
     * Used to output the date
     *
     * @return String
     */
    @Override
    public String toString() {
        return (MONTH_NAMES[month - 1] + " " + this.day + ", " + this.year);
    }

    /**
     * Checks if the year of the current date is a leapYear
     *
     * @return boolean
     */
    public boolean isLeapYear() {
        return ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
    }

    /**
     * Returns the last day of the moth of the current date
     *
     * @return int
     */
    public int lastDayInMonth() {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else if (month == 2) {
            if (isLeapYear()) {
                return 29;
            } else {
                return 28;
            }
        }
        return 0;
        /*
        if (month == 2 && this.isLeapYear()) {
            return 29;
        } else {
            return MONTH_LENGTHS[month-1];
        }
         */
    }

    /**
     * Returns the last day of the month passed to the method
     *
     * @param monthToCheck int
     * @return int
     */
    public int lastDayInMonth(int monthToCheck) {
        if (monthToCheck == 1 || monthToCheck == 3 || monthToCheck == 5 || monthToCheck == 7 || monthToCheck == 8 || monthToCheck == 10 || monthToCheck == 12) {
            return 31;
        } else if (monthToCheck == 4 || monthToCheck == 6 || monthToCheck == 9 || monthToCheck == 11) {
            return 30;
        } else if (monthToCheck == 2) {
            if (isLeapYear()) {
                return 29;
            } else {
                return 28;
            }
        }
        return 0;
    }

    /**
     * Sets the date of the current date to the passed numbers
     *
     * @param monthTmp int
     * @param dayTmp   int
     * @param yearTmp  int
     */
    public void setDate(int monthTmp, int dayTmp, int yearTmp) {
        boolean invalid = false;
        year = yearTmp;
        if (monthTmp < 13 && monthTmp > 0) {
            month = monthTmp;
        } else {
            invalid = true;
        }
        if (!invalid) {
            if (dayTmp > lastDayInMonth(month) || dayTmp < 1) {
                invalid = true;
            } else {
                day = dayTmp;
            }
        }
        if (invalid) {
            invalidDate();
        }
    }

    /**
     * Increments the current date by one day
     */
    public void inc() {
        day++;

        if (day > lastDayInMonth()) {
            day = 1;
            month++;
            if (month > 12) {
                month = 1;
                year++;
            }
        }
    }

    /**
     * Increments the current date by *adder* days
     *
     * @param adder int
     */
    public void inc(int adder) {
        for (int i = 0; i < adder; i++) {
            inc();
        }
    }

    /**
     * Checks if the current date is equal to the passed date
     *
     * @param hello Date
     * @return boolean
     */
    public boolean equals(Date_a hello) {
        boolean storeBool = false;
        if (day == hello.day) {
            storeBool = true;
        }
        if (month == hello.month) {
            storeBool = true;
        }
        if (year == hello.year) {
            storeBool = true;
        }
        return storeBool;
    }

    /**
     * Checks which day the current day is. (in the current year)
     *
     * @return int
     */
    public int dayOfYear() {
        int days = 0;
        for (int i = 1; i < month; i++) {
            days += lastDayInMonth(i);
        }
        days += day;
        return days;
    }

    /**
     * Checks which the day the current day is. (in the current week)
     *
     * @return int
     */
    public int dayOfWeek() {
        Date_a newTestDate = new Date_a(1, 1, 1900);
        int addedDays = 0;
        while (!equals(newTestDate)) {
            newTestDate.inc();
            addedDays++;
        }
        return ((3 + addedDays) % 7);
    }

    /**
     * Is called when the requested date is invalid
     */
    public void invalidDate() {
        year = 1900;
        month = 1;
        day = 1;
    }
}
