package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    public TodoRepository todoRepository() {
        return new TodoRepository() {
            List<Todo> list = new ArrayList<>();
            @Override
            public List<Todo> findAll() {
                return list;
            }

            @Override
            public List<Todo> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<Todo> findAllById(Iterable<Long> longs) {
                return null;
            }

            @Override
            public <S extends Todo> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Todo> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends Todo> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<Todo> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Todo getOne(Long aLong) {
                return null;
            }

            @Override
            public Todo getById(Long aLong) {
                return null;
            }

            @Override
            public Todo getReferenceById(Long aLong) {
                return null;
            }

            @Override
            public <S extends Todo> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Todo> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<Todo> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Todo> S save(S entity) {
                list.add(entity);
                return entity;
            }

            @Override
            public Optional<Todo> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(Todo entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends Todo> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Todo> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Todo> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Todo> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Todo> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends Todo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
    }
}
