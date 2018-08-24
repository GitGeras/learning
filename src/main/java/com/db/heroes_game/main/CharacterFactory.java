package com.db.heroes_game.main;

import com.db.heroes_game.characters.Character;
import com.db.heroes_game.characters.RandomFactory;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CharacterFactory {

    private List<Class<? extends Character>> characterClasses = new ArrayList<>();
    int i = 0;

    public CharacterFactory() {
        Reflections scanner = new Reflections("heroes");
        Set<Class<? extends Character>> set = scanner.getSubTypesOf(Character.class);
        for (Class<? extends Character> clazz : set) {
            if (!Modifier.isAbstract(clazz.getModifiers())){
                characterClasses.add(clazz);
                i++;
            }
        }
    }

    @SneakyThrows
    public Character createCharacter(){
        int random = RandomFactory.getInstance().getNumberBetween(0, i-1);
        return characterClasses.get(random).newInstance();

        /*List<Class> characters = new ArrayList<>();
        characters.add(Elf.class);
        characters.add(Hobbit.class);
        characters.add(King.class);
        characters.add(Knight.class);

        int random = (int)(Math.random()*4);
        Object o = null;
        try {
            o = characters.get(random).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (Character) o;*/
    }
}
