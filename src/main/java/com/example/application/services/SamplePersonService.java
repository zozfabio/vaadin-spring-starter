package com.example.application.services;

import com.example.application.data.SamplePerson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SamplePersonService {

    private final String data = """
        [
            {
            "version": 1,
            "id": 1,
            "first_name": "Eula",
            "last_name": "Lane",
            "email": "eula.lane@jigrormo.ye",
            "phone": "(762) 526-5961",
            "date_of_birth": "1955-03-01",
            "occupation": "Insurance Clerk",
            "role": "Worker",
            "important": false
            },
            {
            "version": 1,
            "id": 2,
            "first_name": "Barry",
            "last_name": "Rodriquez",
            "email": "barry.rodriquez@zun.mm",
            "phone": "(267) 955-5124",
            "date_of_birth": "2014-03-01",
            "occupation": "Mortarman",
            "role": "Manager",
            "important": false
            },
            {
            "version": 1,
            "id": 3,
            "first_name": "Eugenia",
            "last_name": "Selvi",
            "email": "eugenia.selvi@capfad.vn",
            "phone": "(680) 368-2192",
            "date_of_birth": "1974-02-14",
            "occupation": "Beer Coil Cleaner",
            "role": "External",
            "important": false
            },
            {
            "version": 1,
            "id": 4,
            "first_name": "Alejandro",
            "last_name": "Miles",
            "email": "alejandro.miles@dec.bn",
            "phone": "(281) 301-2039",
            "date_of_birth": "2014-04-03",
            "occupation": "Scale Attendant",
            "role": "Worker",
            "important": false
            },
            {
            "version": 1,
            "id": 5,
            "first_name": "Cora",
            "last_name": "Tesi",
            "email": "cora.tesi@bivo.yt",
            "phone": "(600) 616-7955",
            "date_of_birth": "1972-05-31",
            "occupation": "Clinical Audiologist",
            "role": "Supervisor",
            "important": false
            },
            {
            "version": 1,
            "id": 6,
            "first_name": "Marguerite",
            "last_name": "Ishii",
            "email": "marguerite.ishii@judbilo.gn",
            "phone": "(882) 813-1374",
            "date_of_birth": "1938-02-26",
            "occupation": "Parking Meter Collector",
            "role": "Supervisor",
            "important": false
            },
            {
            "version": 1,
            "id": 7,
            "first_name": "Mildred",
            "last_name": "Jacobs",
            "email": "mildred.jacobs@joraf.wf",
            "phone": "(642) 665-1763",
            "date_of_birth": "1967-10-01",
            "occupation": "Business Unit Manager",
            "role": "Manager",
            "important": false
            },
            {
            "version": 1,
            "id": 8,
            "first_name": "Gene",
            "last_name": "Goodman",
            "email": "gene.goodman@kem.tl",
            "phone": "(383) 458-2132",
            "date_of_birth": "2010-08-11",
            "occupation": "Technical Communicator",
            "role": "External",
            "important": false
            },
            {
            "version": 1,
            "id": 9,
            "first_name": "Lettie",
            "last_name": "Bennett",
            "email": "lettie.bennett@odeter.bb",
            "phone": "(769) 335-6771",
            "date_of_birth": "1959-10-16",
            "occupation": "Correctional Officer Sergeant",
            "role": "Worker",
            "important": false
            },
            {
            "version": 1,
            "id": 10,
            "first_name": "Mabel",
            "last_name": "Leach",
            "email": "mabel.leach@lisohuje.vi",
            "phone": "(803) 586-8035",
            "date_of_birth": "1946-09-22",
            "occupation": "Food Chemist",
            "role": "Supervisor",
            "important": false
            },
            {
            "version": 1,
            "id": 11,
            "first_name": "Jordan",
            "last_name": "Miccinesi",
            "email": "jordan.miccinesi@duod.gy",
            "phone": "(531) 919-2280",
            "date_of_birth": "1982-11-03",
            "occupation": "Signals Intelligence/Electronic Warfare Chief",
            "role": "Manager",
            "important": false
            },
            {
            "version": 1,
            "id": 12,
            "first_name": "Marie",
            "last_name": "Parkes",
            "email": "marie.parkes@nowufpus.ph",
            "phone": "(814) 667-8937",
            "date_of_birth": "1943-09-04",
            "occupation": "Language Pathologist",
            "role": "External",
            "important": false
            },
            {
            "version": 1,
            "id": 13,
            "first_name": "Rose",
            "last_name": "Gray",
            "email": "rose.gray@kagu.hr",
            "phone": "(713) 311-8766",
            "date_of_birth": "1958-09-03",
            "occupation": "Wildlife Officer",
            "role": "Worker",
            "important": false
            },
            {
            "version": 1,
            "id": 14,
            "first_name": "Garrett",
            "last_name": "Stokes",
            "email": "garrett.stokes@fef.bg",
            "phone": "(381) 421-2371",
            "date_of_birth": "2009-06-14",
            "occupation": "Bindery Machine Operator",
            "role": "Manager",
            "important": false
            },
            {
            "version": 1,
            "id": 15,
            "first_name": "Barbara",
            "last_name": "Matthieu",
            "email": "barbara.matthieu@derwogi.jm",
            "phone": "(940) 463-7299",
            "date_of_birth": "1930-06-10",
            "occupation": "Instructional Aide",
            "role": "External",
            "important": false
            },
            {
            "version": 1,
            "id": 16,
            "first_name": "Jean",
            "last_name": "Rhodes",
            "email": "jean.rhodes@wehovuce.gu",
            "phone": "(777) 435-9570",
            "date_of_birth": "1949-11-17",
            "occupation": "Clinical Psychiatrist",
            "role": "Worker",
            "important": false
            },
            {
            "version": 1,
            "id": 17,
            "first_name": "Jack",
            "last_name": "Romoli",
            "email": "jack.romoli@zamum.bw",
            "phone": "(517) 393-9630",
            "date_of_birth": "1975-09-13",
            "occupation": "Mortician Investigator",
            "role": "Supervisor",
            "important": false
            },
            {
            "version": 1,
            "id": 18,
            "first_name": "Pearl",
            "last_name": "Holden",
            "email": "pearl.holden@dunebuh.cr",
            "phone": "(711) 904-3669",
            "date_of_birth": "1950-01-08",
            "occupation": "Rod Buster Helper",
            "role": "Manager",
            "important": false
            },
            {
            "version": 1,
            "id": 19,
            "first_name": "Belle",
            "last_name": "Montero",
            "email": "belle.montero@repiwid.si",
            "phone": "(935) 404-4792",
            "date_of_birth": "1933-01-31",
            "occupation": "Classroom Aide",
            "role": "External",
            "important": false
            },
            {
            "version": 1,
            "id": 20,
            "first_name": "Olive",
            "last_name": "Molina",
            "email": "olive.molina@razuppa.ga",
            "phone": "(935) 267-8492",
            "date_of_birth": "1934-08-13",
            "occupation": "Traditional Chinese Herbalist",
            "role": "Worker",
            "important": false
            }
        ]
        """;
    private final List<SamplePerson> persons;

    public SamplePersonService(ObjectMapper jsonMapper) {
        try {
            this.persons = jsonMapper.readValue(
                data, jsonMapper.getTypeFactory()
                    .constructCollectionType(List.class, SamplePerson.class)
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<SamplePerson> get(Long id) {
        return persons.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public SamplePerson update(SamplePerson entity) {
        return persons.stream()
            .filter(p -> p.getId().equals(entity.getId()))
            .peek(p -> {
                p.setFirstName(entity.getFirstName());
                p.setLastName(entity.getLastName());
                p.setEmail(entity.getEmail());
                p.setPhone(entity.getPhone());
                p.setDateOfBirth(entity.getDateOfBirth());
                p.setOccupation(entity.getOccupation());
                p.setRole(entity.getRole());
                p.setImportant(entity.isImportant());
            })
            .findFirst()
            .orElseThrow();
    }

    public void delete(Long id) {
        persons.removeIf(p -> p.getId().equals(id));
    }

    public Page<SamplePerson> list(Pageable pageable) {
        return new PageImpl<>(persons, pageable, persons.size());
    }

    public int count() {
        return persons.size();
    }
}
