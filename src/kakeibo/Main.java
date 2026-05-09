package kakeibo;


//標準ライブラリ
import java.util.Scanner;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AccountBook acbook = new AccountBook();

        File dir = new File("data");
        dir.mkdirs(); 
        
        acbook.loadFromCSV(); //起動時CSV読み込み

        while (true) {
            System.out.println("1:登録 2:一覧 3:合計 4:保存 5:変更/削除 0:終了");
            int menu = inputInt(sc);

            switch (menu) {
                case 0://終了（確認）
                    while (true) { //ｙかｎ選択までループ
                        System.out.print("保存しますか？(y/n): ");
                        String answer = sc.next();

                        if (answer.equalsIgnoreCase("y")) {
                            acbook.saveToCSV();
                            break;
                        } else if (answer.equalsIgnoreCase("n")) {
                            break;
                        } else {
                            System.out.println("yかnで入力してください");
                        }
                    }

                    System.out.println("終了します");
                    return;
                    
                case 1://登録 
                    PL_Element data = inputData(sc);
                    acbook.add(data);
                    break;

                case 2://一覧
                    acbook.showAll();
                    break;

                case 3://合計
                    acbook.showTotal();
                    break;

                case 4://保存
                    //保存最終確認
                    boolean save = inputYesNo(sc, "保存しますか？(y/n): ");
                    if (save) {
                        acbook.saveToCSV();
                    }

                    break;

                case 5://変更/削除
                    while (true) {
                        System.out.println("1:変更 2:削除 0:戻る");
                        int subMenu = inputInt(sc);

                        if (subMenu == 0) break;

                        if (subMenu == 1) {
                            // 変更
                                acbook.showAll();

                                System.out.print("変更するID: ");
                                int id = sc.nextInt();

                                //IDチェック
                                if (acbook.findById(id) == null) {
                                    System.out.println("そのIDは存在しません");
                                    break;
                                }
  
                                PL_Element newData = inputData(sc);

                                //アップデート最終確認
                                boolean newupdate = inputYesNo(sc, "本当に更新しますか？(y/n): ");
                                if (newupdate) {
                                    acbook.update(id, newData);
                                    acbook.saveToCSV();
                                }

                        } else if (subMenu == 2) {
                            // 削除
                                acbook.showAll();
                                System.out.print("削除するID: ");
                                int id = sc.nextInt();

                                //IDチェック
                                if (acbook.findById(id) == null) {
                                    System.out.println("そのIDは存在しません");
                                    break;
                                }
                                
                                //削除最終確認
                                boolean confirm = inputYesNo(sc, "本当に削除しますか？(y/n): ");
                                if (confirm) {
                                    acbook.deleteById(id);
                                    acbook.saveToCSV();
                                }

                        } else {
                            System.out.println("無効な入力");
                        }
                    }

                    break;

                default://選択肢以外の処理
                    System.out.println("無効な入力です");
                    break;
            }
        }
    }

//============メニュー関連========================================================================
   //数値入力（メニュー選択時に使用）
    public static int inputInt(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (Exception e) {
                System.out.println("数字を入力してください");
                sc.next();
            }
        }
    }
    //YesNoチェック
    public static boolean inputYesNo(Scanner sc, String message) {
        while (true) {
            System.out.print(message);

            String input = sc.next();

            if (input.equalsIgnoreCase("y")) {
                return true;
            } else if (input.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("y か n を入力してください");
            }
        }
    }

//============入力関連========================================================================
    //登録、更新
    public static PL_Element inputData(Scanner sc) {
        String date = inputDate(sc);
        String category = inputCategory(sc);
        int amount = inputAmount(sc);
        boolean isIncome = inputType(sc);

        return new PL_Element(date, category, amount, isIncome);
    }

    //日付入力
    public static String inputDate(Scanner sc) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        while (true) {
            System.out.print("日付(yyyyMMdd): ");
            String input = sc.next();

            try {
                LocalDate.parse(input, formatter);
                return input;
            } catch (DateTimeParseException e) {
                System.out.println("正しい日付を入力してください（例: 20260509）");
            }
        }
    }

    //カテゴリ入力
    public static String inputCategory(Scanner sc) {
        System.out.print("カテゴリ: ");
        return sc.next();
    }

    // 収支入力
    public static boolean inputType(Scanner sc) {
        return inputIncomeFlag(sc, "収入？(1:収入 / 0:支出): ");
    }

    //　金額入力チェック
    public static int inputAmount(Scanner sc) {
        while (true) {
            System.out.print("金額: ");
            String input = sc.next();

            if (!input.matches("\\d+")) {
                System.out.println("数字のみ入力してください");
                continue;
            }

            int amount = Integer.parseInt(input);

            if (amount < 0) {
                System.out.println("0以上の金額を入力してください");
                continue;
            }

            return amount;
        }
    }

    //　収支入力チェック
    public static boolean inputIncomeFlag(Scanner sc, String message) {
        while (true) {
            System.out.print(message);

            try {
                int type = sc.nextInt();

                if (type == 1) return true;
                if (type == 0) return false;

                System.out.println("0か1を入力してください");

            } catch (Exception e) {
                System.out.println("数字を入力してください");
                sc.next();
            }
        }
    }

}