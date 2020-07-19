package com.higor.make_magic.domain.service.definition;

import com.higor.make_magic.domain.dto.CharacterDTO;
import com.higor.make_magic.domain.entity.Character;

import java.util.List;

public interface CharacterServiceDefinition {

    public List<Character> getAll(String house, String patronus);

    public Character getById(Long id);

    public Character create(CharacterDTO characterDTO);

    public Character update(Long id, CharacterDTO characterDTO);

    public void delete(Long id);
}
