package com.li88qq.service.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * poi工具类
 *
 * @author li88qq
 * @version 1.0 2021/9/7 23:26
 */
public class PoiUtil {

    //doc http://poi.apache.org/components/spreadsheet/quick-guide.html

    //生成备注

    /**
     * 合并单元格
     *
     * @param sheet    单元表
     * @param firstRow 开始行 0开始
     * @param lastRow  结束行,0开始
     * @param firstCol 开始列,0开始
     * @param lastCol  结束列,0开始
     */
    public static void merged(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    }

    /**
     * 冻结单元格
     *
     * @param sheet    单元表
     * @param firstRow 开始行 0开始
     * @param lastRow  结束行,0开始,不包括该行,
     * @param firstCol 开始列,0开始
     * @param lastCol  结束列,0开始,不包括该列
     */
    public static void freezePane(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        sheet.createFreezePane(firstRow, lastRow, firstCol, lastCol);
    }
    //样式
    //下拉框

}
