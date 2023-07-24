import java.util.List;

public class BookMain {
    public static void main(String[] args) {
        BookUI bookUI = new BookUI();
        BookDao bookDao = new BookDao("/temp/books1.dat");
        List<Book> books = bookDao.getBooks();

        // 판매 도서
        BookDao soldBookDao = new BookDao("/temp/sold-books.dat");
        List<Book> soldBooks = soldBookDao.getBooks();

        while (true) {
            int menuId = bookUI.menu();
            if (menuId == 6) {
                System.out.println("종료합니다");
                bookDao.saveBooks(books);
                break;
            } else if (menuId == 1) {
                Book book = bookUI.regBook();
                books.add(book);
                System.out.println("등록되었습니다.");

            } else if (menuId == 2) {
                bookUI.printBookList(books);

            } else if (menuId == 3) {
                String title = bookUI.inputDCTitle();
                int findIndex = -1;
                for (int i = 0; i < books.size(); i++) {
                    Book b = books.get(i);
                    if (b.getTitle().equals(title)) {
                        findIndex = i;
                        break;
                    }
                }
                if (findIndex != -1) {
                    Book updateBook = bookUI.inputBook(title);
                    books.remove(findIndex);
                    books.add(updateBook);
                    System.out.println("해당도서 할인 적용되었습니다.");
                } else {
                    System.out.println("해당 도서가 없습니다");
                }


            } else if (menuId == 4) {
                String title2 = bookUI.inputSoldOutTitle();
                int findIndex = -1;

                for (int i = 0; i < books.size(); i++) {
                    Book bb = books.get(i);
                    if (bb.getTitle().equals(title2)) {
                        findIndex = i;
                        break;
                    }

                }
                if (findIndex != -1) {
                    Book found = books.get(findIndex);
                    soldBooks.add(found);
//                    System.out.println(found);//

                    books.remove(findIndex);
                    System.out.println("판매되었습니다.");
                } else {
                    System.out.println("판매할 도서가 등록되어있지않습니다. 다시 입력해주세요");
                }
            } else if (menuId==5) {
                bookUI.printSoldBookList(soldBooks);

            }
        }
    }
}

