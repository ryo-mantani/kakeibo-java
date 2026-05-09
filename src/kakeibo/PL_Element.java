package kakeibo;

//損益要素のデータクラス
public class PL_Element {
    int id;             //管理用ID
    String date;        //日付
    String category;    //収入（給与、賞与）・支出（食費、交通費）区分
    int amount;         //金額
    boolean PLflag;      //損益（収入；true　支出：false）

    private static int nextId = 1; //id加算用

    //===PL_Elementオーバーロード
    //新規登録用
    public PL_Element(String date, String category, int amount, boolean PLflag) {
        this.id = nextId++;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.PLflag = PLflag;
    }
    //CSV読み込み用
    public PL_Element(int id, String date, String category, int amount, boolean PLflag) {
        this.id = id;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.PLflag = PLflag;

        if (id >= nextId) {
            nextId = id + 1;
        }
    }

    //=========データ取得用=========
    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isPLflag() {
        return PLflag;
    }

    //=========データセット用=========
    public void setDate(String date) {
        this.date = date;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPLflag(boolean PLflag) {
        this.PLflag = PLflag;
    }


} 