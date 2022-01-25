package com.itheima.pinda.goods.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Excel 工具类
 *
 * @author zhangshichang
 * @date 19-2-25 下午2:25
 */
@Slf4j
//public class ExcelUtils {
//    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,String fileName,boolean isCreateHeader, HttpServletResponse response){
//        ExportParams exportParams = new ExportParams(title, sheetName);
//        exportParams.setCreateHeadRows(isCreateHeader);
//        defaultExport(list, pojoClass, fileName, response, exportParams);
//
//    }
//    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,String fileName, HttpServletResponse response){
//        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
//    }
//    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response){
//        defaultExport(list, fileName, response);
//    }
//
//    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response, ExportParams exportParams) {
//        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,pojoClass,list);
//        if (workbook != null);
//        downLoadExcel(fileName, response, workbook);
//    }
//
//    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
//        try {
//            response.setCharacterEncoding("UTF-8");
//            response.setHeader("content-Type", "application/vnd.ms-excel");
//            response.setHeader("Content-Disposition",
//                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//            workbook.write(response.getOutputStream());
//        } catch (IOException e) {
//            throw new BizException(e.getMessage());
//        }
//    }
//    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
//        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
//        if (workbook != null);
//        downLoadExcel(fileName, response, workbook);
//    }
//
//    public static <T> List<T> importExcel(String filePath,Integer titleRows,Integer headerRows, Class<T> pojoClass){
//        if (StringUtils.isBlank(filePath)){
//            return null;
//        }
//        ImportParams params = new ImportParams();
//        params.setTitleRows(titleRows);
//        params.setHeadRows(headerRows);
//        List<T> list = null;
//        try {
//            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
//        }catch (NoSuchElementException e){
//            throw new BizException("模板不能为空");
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new BizException(e.getMessage());
//        }
//        return list;
//    }
//    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass){
//        if (file == null){
//            return null;
//        }
//        ImportParams params = new ImportParams();
//        params.setTitleRows(titleRows);
//        params.setHeadRows(headerRows);
//        List<T> list = null;
//        try {
//            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
//        }catch (NoSuchElementException e){
//            throw new BizException("excel文件不能为空");
//        } catch (Exception e) {
//            throw new BizException(e.getMessage());
//        }
//        return list;
//    }
//}
public class ExcelUtils {
    /**
     *
     * @Title: importData
     * @Description: 导入excle 数据
     * @param file  文件
     * @param headerRows  忽略头行数
     * @param pojoClass   转换的实体
     * @return List<User>  返回的集合
     */
    public static <T> List<T> importData(MultipartFile file, Integer headerRows,
                                         Class<T> pojoClass){
        if (file == null) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     * @Title: exportExcel
     * @Description: 导出excel
     * @param list  导出的数据
     * @param title  文件标题
     * @param sheetName  sheet名称
     * @param pojoClass  集合的类
     * @param fileName   文件名
     * @param response
     * @return void
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(title, sheetName), pojoClass, list);
        if (workbook != null) {
            try {
                response.setCharacterEncoding("UTF-8");
                response.setHeader("content-Type", "application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
                workbook.write(response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}