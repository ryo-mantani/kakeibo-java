Skip to content
ryo-mantani
kakeibo-java
Repository navigation
Code
Issues
Pull requests
Actions
Projects
Wiki
Security and quality
Insights
Settings
Files
Go to file
t
T
data
src
kakeibo
README.md
.gitignore
kakeibo-java/src
/
README.md
in
main

Edit

Preview
Indent mode

Spaces
Indent size

2
Line wrap mode

Soft wrap
Editing README.md file contents
  1
  2
  3
  4
  5
  6
  7
  8
  9
 10
 11
 12
 13
 14
 15
 16
 17
 18
 19
 20
 21
 22
 23
 24
 25
 26
 27
 28
 29
 30
 31
 32
# ryo-mantani-java
簡易家計簿

## 📌 概要
コマンドライン上で動作するシンプルな家計簿アプリです。  
収入・支出の登録や合計計算、CSVによるデータ保存ができます。

## 🛠 使用技術
- Java
- Git / GitHub

## ✨ 機能
- 収入・支出の登録
- 一覧表示
- 合計金額の自動計算
- CSVファイルへの保存・読み込み

## 💡 工夫した点
- クラス設計でデータを管理（AccountBook / PL_Element）
- 入力チェック処理を実装
- CSVでデータを永続化

## 🚀 実行方法
javac src\kakeibo\*.java

java -cp src kakeibo.Main

## 📈 今後の改善
- 例外処理の強化
- GUI化（JavaFXなど）
- データ検索機能の追加

Use Control + Shift + m to toggle the tab key moving focus. Alternatively, use esc then tab to move to the next interactive element on the page.
選択されていません
Attach files by dragging & dropping, selecting or pasting them.
src content loaded
 
