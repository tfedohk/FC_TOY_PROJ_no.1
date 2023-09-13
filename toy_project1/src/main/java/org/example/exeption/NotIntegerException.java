package org.example.exeption;

public class NotIntegerException extends Exception{
    public NotIntegerException() {
        super();
    }

    @Override
    public void printStackTrace() {
        System.out.println("| 입력값이 정수가 아닙니다. 다시 입력해주세요.");
    }
}