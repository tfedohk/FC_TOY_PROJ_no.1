package org.example.exeption;

public class FileNotExistException extends Exception {
    public FileNotExistException() {
        super();
    }

    @Override
    public void printStackTrace() {
        System.out.println("| 불러올 여행 기록이 없습니다. 메인으로 돌아갑니다.");
    }
}
