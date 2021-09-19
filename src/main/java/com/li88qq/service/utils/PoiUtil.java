package com.li88qq.service.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    // 表头样式
    private static CellStyle initHeadStyle(Workbook wb) {
        CellStyle cellStyle = wb.createCellStyle();

        cellStyle.setFillForegroundColor(IndexedColors.GREEN.index); // 设置背景色
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 设置垂直居中
        cellStyle.setWrapText(true);

        Font font = wb.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 9);// 设置字体大小
        font.setBold(true);// 粗体显示
        font.setColor(IndexedColors.WHITE.index);
        cellStyle.setFont(font);
        return cellStyle;
    }

    // 设置单元格备注
    private static void setComment(Sheet sheet, Cell cell, String text) {
        Drawing p = sheet.createDrawingPatriarch();
        Comment comment = p.createCellComment(new XSSFClientAnchor());
        // 输入批注信息
        comment.setString(new XSSFRichTextString(text));
        // 批注人员
        // comment.setAuthor("");
        cell.setCellComment(comment);
    }

    // 设置下拉框
    private static void addValidation(Workbook wb, Sheet sheet, int[] range, Sheet hidden, String name, int cellIndex, String[] datas) {
        String indexValue = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // 在隐藏sheet生成数据
        for (int i = 0; i < datas.length; i++) {
            Row row = hidden.getRow(i);
            if (row == null) {
                row = hidden.createRow(i);
            }
            Cell cell = row.createCell(cellIndex);
            cell.setCellValue(datas[i]);
        }
        CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(range[0], range[1], range[2], range[3]);

        Name namedCell = wb.createName();
        namedCell.setNameName(name);
        String referCell = String.valueOf(indexValue.charAt(cellIndex));
        String refer = hidden.getSheetName() + "!$" + referCell + "$1:$" + referCell + "$" + datas.length;// 关联表达式
        namedCell.setRefersToFormula(refer);

        DataValidationHelper dataValidationHelper = sheet.getDataValidationHelper();
        DataValidationConstraint constraint = dataValidationHelper.createFormulaListConstraint(name);

        DataValidation validation = dataValidationHelper.createValidation(constraint, cellRangeAddressList);

        sheet.addValidationData(validation);
    }

    /**
     * 获取cell的字符串值
     *
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        try {
            String value = "";
            if (cell.getCellType() == CellType.NUMERIC) { // 数字格式的时候转换成字符串
                value = BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();
            } else {
                value = cell.getStringCellValue();
            }
            return value.trim();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 返回日期
     *
     * @param date
     * @return
     */
    public static LocalDate getDateValue(String date) {
        if (date == null || date.equals("")) {
            return null;
        }
        // 正则 yyyy-MM-dd yyyy/MM/dd
        String regx = "\\d{4}([-/]\\d{1,2}){2}";
        if (!date.matches(regx)) {
            return null;
        }
        String[] arr = date.split("[-/]");
        if (arr[1].length() == 1) {
            arr[1] = "0" + arr[1];
        }
        if (arr[2].length() == 1) {
            arr[2] = "0" + arr[2];
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(String.join("-", arr), formatter);
        } catch (Exception e) {
            return null;
        }

    }
}
