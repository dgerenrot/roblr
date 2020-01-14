package org.roblr.generator.defaults;

import org.roblr.generator.Generator;

import java.util.Calendar;
import java.util.Date;

public class DefaultDateGenerator implements Generator<Date> {

    // TODO: Date ranges, gen with specific time of day, top of hour, half-hour, etc.
    @Override
    public Date generate() {
        int year = 2000 + Rng.instance().nextInt(20);
        int date = Rng.instance().nextInt(28) + 1;
        int month = Rng.instance().nextInt(10) + 1;

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date);

        return calendar.getTime();
    }
}
