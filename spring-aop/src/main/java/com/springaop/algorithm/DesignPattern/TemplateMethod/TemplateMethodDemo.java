package com.springaop.algorithm.DesignPattern.TemplateMethod;

import com.springaop.algorithm.DesignPattern.TemplateMethod.obj.BookMapper;
import com.springaop.algorithm.DesignPattern.TemplateMethod.obj.Dota;
import com.springaop.algorithm.DesignPattern.TemplateMethod.obj.Football;

import java.util.List;

public class TemplateMethodDemo {
    public static void main(String[] args) {
        GameplayTemplate game = new Dota();
        game.play();
        System.out.println();
        game = new Football();
        game.play();

        List<String> queryResult = new QueryTemplate().excuteQuery("query", new BookMapper());

        NetworkTemplate network = new Facebook();
        network.sendMessage("facebook message");
    }
}
