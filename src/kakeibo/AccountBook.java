package kakeibo;

//=======標準ライブラリ=======
//入力時に使用
import java.util.ArrayList;
import java.util.List;

//CSV書き込み時に使用
import java.io.FileWriter;

//CSV読み込み時に使用
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

//入出力チェック
import java.io.IOException;

//=======クラス=======
public class AccountBook {
    //=======フィールド=======
    List<PL_Element> list = new ArrayList<>();
    private int nextId = 1;

    //=======メソッド=======
    //　登録
    public void add(PL_Element date) {
        list.add(date);
        nextId++;
    }

    //　一覧表示
    public void showAll() {
        for (PL_Element t : list) {
            String type = t.PLflag ? "収入" : "支出";
            System.out.println(t.id + ":" + t.date + " / " + type + " / " + t.category + " / " + t.amount);
        }
    }

    //　合計計算
    public void showTotal() {
        int income = 0;  //収入
        int expense = 0; //支出

        for (PL_Element t : list) {
            if (t.PLflag) {
                income += t.amount;
            } else {
                expense += t.amount;
            }
        }

        System.out.println("収入合計: " + income);
        System.out.println("支出合計: " + expense);
        System.out.println("残高: " + (income - expense));
    }

    //　CSV読み込み
    public void loadFromCSV() {
        File file = new File("data/kakeibo.csv");

        if (!file.exists()) {
            System.out.println("CSVなし（初回起動）");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String date = data[1];
                String category = data[2];
                int amount = Integer.parseInt(data[3]);
                boolean PLflag = Boolean.parseBoolean(data[4]);

                list.add(new PL_Element(id, date, category, amount, PLflag));

                if (id >= nextId) { //id加算
                    nextId = id + 1;
                }
            }

        } catch (Exception e) {
            System.out.println("CSV読み込みエラー: " + e.getMessage());
        }
    }

    //　保存処理
    public void saveToCSV() {
        File file = new File("data");
        
        if (!file.exists()) {
            file.mkdirs();
        }

        try (FileWriter fw = new FileWriter("data/kakeibo.csv", false)) {

            for (PL_Element t : list) {
                fw.write(t.id + "," + t.date + "," + t.category + "," + t.amount + "," + t.PLflag + "\n");
            }

            fw.close();
            System.out.println("CSV保存完了");
        
        } catch (IOException e) {
            System.out.println("エラー: " + e.getMessage()); //保存時エラー処理
        }
    }

    //　IDチェック
    public PL_Element findById(int id) {
        for (PL_Element t : list) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    //　削除
    public void deleteById(int id) {
        boolean removed = list.removeIf(t -> t.id == id);

        if (removed) {
            System.out.println("削除しました");
        } else {
            System.out.println("該当IDなし");
        }
    }

    //　変更
    public void update(int id, PL_Element newData) {
        PL_Element target = findById(id);

        if (target != null) {
            target.setDate(newData.getDate());
            target.setCategory(newData.getCategory());
            target.setAmount(newData.getAmount());
            target.setPLflag(newData.isPLflag());
            System.out.println("更新しました");
            return;
        }


        System.out.println("該当IDなし");
    }

}


