# ryo-mantani-java
家計簿アプリ（Java CLI）

## 📌 概要
コマンドライン上で動作するシンプルな家計簿アプリです。  
収入・支出の登録や合計計算、CSVによるデータ保存ができます。

## 🛠 使用技術
- Java
- Git / GitHub
- Visual Studio Code
- 
## ✨ 機能
- 収入・支出の登録
- 一覧表示
- 合計金額の自動計算
- CSVファイルへの保存・読み込み

## 💡 工夫した点
- クラス設計でデータを管理（AccountBook / PL_Element）
- 入力チェック処理（数値・日付）を実装
- 例外処理を導入し、異常入力やファイルエラー時でも動作継続する設計
- CSVでデータを永続化

## 🚀 実行方法
javac -d . src\kakeibo\*.java

java kakeibo.Main

## 📈 今後の改善
- 例外処理の強化
- GUI化（JavaFXなど）
- データ検索機能の追加
