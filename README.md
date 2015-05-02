# AccountingBook (記帳本)
Digital System Lab 1, **Group 15**

## 支援 Android 版本
Android 4.1+ (API 16 ~ API 22)
> SDK 版本為 22

## 使用說明
- 在主畫面點選右上角的 **"+"** 可以新增一個記帳項目。
- 在新增畫面可以輸入描述、花費、日期等等。
- 在詳細列表畫面長按項目可以選擇，選擇完後按右上角的**垃圾桶**可以刪除。
- 在概覽畫面可以隨時檢查餘額。

## 檔案說明
- java
    - MainActivity      : 主畫面 activity
    - AddActivity       : 新增記帳項目的 activity
    - Record            : 每一筆資料的 type
    - AccountingBook    : 記帳本，存取資料庫
    - MySQLiteHelper    : 建立資料庫
    - OverviewFragment  : 顯示餘額的 fragment
    - RecordFragment    : 顯示記帳列表的 fragment
    - ListViewAdapter   : 決定 ListView 中的 item 如何顯示
- layout
    - activity_main.xml     : 主畫面的介面
    - activity_add.xml      : 新增項目表單的介面
    - fragment_overview.xml : 餘額介面
    - listview_item.xml     : ListView 中 item 的 layout
