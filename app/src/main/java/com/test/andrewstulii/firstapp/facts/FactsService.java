package com.test.andrewstulii.firstapp.facts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by andrewstulii.
 */
public class FactsService {

    private List<Fact> facts = new ArrayList<Fact>() {{
        add(new Fact("A female dolphin will assist in the birth of another’s baby dolphin",
                "Самка дельфина может помогать при родах другой самке", true));
        add(new Fact("If you Google ‘Zerg Rush’ Google will eat up the search results",
                "Если написать в Гугле ‘Zerg Rush’, то гугл сьест все найденные варианты", true));
        add(new Fact("Temperature of body can not affect appetite",
                "Температура тела не влияет на аппетит", false));
        add(new Fact("Putting sugar on a cut or wound reduces pain and speed up the healing process",
                "Посыпав сахар на рану или порез можно уменьшить боль и ускорить заживление", true));
        add(new Fact("Isaac Newton invented the game Hopscotch",
                "Исаак Ньютон придумал игру в классики", false));
        add(new Fact("By law, all globes in Australia are displayed upside down",
                "По закону в Австралии глобус показывается вверх ногами", false));
        add(new Fact("The Great Wall of China Is the Only Man-made Object Visible from Space",
                "Великая Китайская Стена это единственный, построенный человеком, обьект, который видно из космоса", false));
        add(new Fact("A crocodile can't stick it's tongue out",
                "Крокодил не может высунуть язык", true));
        add(new Fact("More than 50% of the people in the world have never made or received a telephone call",
                "Более 50% людей никогда не получали телефонного звонка", true));
        add(new Fact("It is physically possible for pigs to look up into the sky",
                "Чисто физически свиньи могут посмотреть в небо", false));
        add(new Fact("Trilogy 'The Hitchhiker's Guide to the Galaxy' consist of 5 books",
                "Трилогия 'Автостопом по галактике' состоит из 5 книг", true));
    }};

    public Fact getRandomFact() {
        return facts.get(new Random().nextInt(facts.size()));
    }
}
