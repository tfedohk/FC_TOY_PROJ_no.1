package org.example.exeption;

public class NotAppropriateOptionException extends Exception{
    public NotAppropriateOptionException() {
        super();
    }

    @Override
    public void printStackTrace() {
        System.out.println("| 리스트에 있는 번호를 입력해주세요.");
    }
}