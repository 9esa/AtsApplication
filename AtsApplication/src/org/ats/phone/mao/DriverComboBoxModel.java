package org.ats.phone.mao;

import org.ats.phone.dao.DriverEntity;

/**
 * Created by user on 10.04.17.
 */
public class DriverComboBoxModel extends DriverEntity {

    @Override
    public String toString() {
        return getName() + " " + getSecondName() + " " + getPhone();
    }
}
