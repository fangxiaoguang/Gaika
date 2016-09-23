package com.game.gaika.data;

import com.game.gaika.data.weapon.BaseWeapon;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.game.gaika.data.ID.SORT_ASCEND.DOWN;
import static com.game.gaika.data.ID.STOR_TYPE.BUY_COST;
import static com.game.gaika.data.ID.STOR_TYPE.COUNTRY;
import static com.game.gaika.data.ID.STOR_TYPE.LEVEL;
import static com.game.gaika.data.ID.STOR_TYPE.NAME;
import static com.game.gaika.data.ID.STOR_TYPE.REPAIR_COST;
import static com.game.gaika.data.ID.STOR_TYPE.SELL_COST;
import static com.game.gaika.data.ID.STOR_TYPE.SUPPLY;
import static com.game.gaika.data.ID.STOR_TYPE.TYPE;

/**
 * Created by fangxg on 2015/8/7.
 */
public class SortManager {
    public static void sortWeapons(List<BaseWeapon> retNodes, ID.STOR_TYPE pSortType, final ID.SORT_ASCEND pAscend) {
        if (pSortType == TYPE) {
            Collections.sort(retNodes, new Comparator<BaseWeapon>() {

                @Override
                public int compare(BaseWeapon arg0, BaseWeapon arg1) {

                    int ret = arg0.info.type.compareTo(arg1.info.type);
                    if (ret == 0) {
                        if (arg0.info.id > arg1.info.id) {
                            ret = -1;
                        } else if (arg0.info.id < arg1.info.id) {
                            ret = 1;
                        }
                    }

                    if (pAscend == DOWN) {
                        ret = -ret;
                    }

                    return ret;
                }
            });

        } else if (pSortType == NAME) {
            Collections.sort(retNodes, new Comparator<BaseWeapon>() {

                @Override
                public int compare(BaseWeapon arg0, BaseWeapon arg1) {

                    int ret = arg0.info.name.compareTo(arg1.info.name);

                    if (pAscend == DOWN) {
                        ret = -ret;
                    }

                    return ret;
                }

            });

        } else if (pSortType == COUNTRY) {
            Collections.sort(retNodes, new Comparator<BaseWeapon>() {

                @Override
                public int compare(BaseWeapon arg0, BaseWeapon arg1) {

                    int ret = arg0.info.country.compareTo(arg1.info.country);
                    if (ret == 0) {
                        if (arg0.info.id > arg1.info.id) {
                            ret = -1;
                        } else if (arg0.info.id < arg1.info.id) {
                            ret = 1;
                        }
                    }
                    if (pAscend == DOWN) {
                        ret = -ret;
                    }

                    return ret;
                }

            });

        } else if (pSortType == BUY_COST || pSortType == REPAIR_COST || pSortType == SELL_COST
                || pSortType == SUPPLY) {
            Collections.sort(retNodes, new Comparator<BaseWeapon>() {

                @Override
                public int compare(BaseWeapon arg0, BaseWeapon arg1) {

                    int ret = 0;
                    if (arg0.info.price > arg1.info.price) {
                        ret = -1;
                    } else if (arg0.info.price < arg1.info.price) {
                        ret = 1;
                    } else {
                        if (arg0.info.id > arg1.info.id) {
                            ret = -1;
                        } else if (arg0.info.id < arg1.info.id) {
                            ret = 1;
                        }
                    }
                    if (pAscend == DOWN) {
                        ret = -ret;
                    }
                    return ret;
                }
            });

        } else if (pSortType == LEVEL) {
            Collections.sort(retNodes, new Comparator<BaseWeapon>() {

                @Override
                public int compare(BaseWeapon arg0, BaseWeapon arg1) {
                    int ret = 0;

                    if (arg0.exp > arg1.exp) {
                        ret = 1;
                    } else if (arg0.exp < arg1.exp) {
                        ret = -1;
                    }
                    if (pAscend == DOWN) {
                        ret = -ret;
                    }
                    return ret;
                }
            });
        }
    }
}
