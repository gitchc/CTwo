package com.timory.utils;

/*
* 根据表达式换颜色
* 需要Poi支持
* */
public class ExcelFormat {
    public static void main(String[] args) {
        String str = "[red][<=60] A#,##0.00_);[blue][<90] B#,##0.00_);[green] C#,##0.00_)";
       /* CellFormat cfmt = CellFormat.getInstance(str);
        CellFormatResult apply = cfmt.apply(1000);
        System.out.println(apply.textColor.getRed() + "," + apply.textColor.getGreen() + "," + apply.textColor.getBlue());*/
    }
}
