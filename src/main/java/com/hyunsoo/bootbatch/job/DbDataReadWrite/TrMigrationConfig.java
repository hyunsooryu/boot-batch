package com.hyunsoo.bootbatch.job.DbDataReadWrite;


import com.hyunsoo.bootbatch.core.domain.accounts.Accounts;
import com.hyunsoo.bootbatch.core.domain.accounts.AccountsRepository;
import com.hyunsoo.bootbatch.core.domain.orders.Orders;
import com.hyunsoo.bootbatch.core.domain.orders.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class TrMigrationConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final OrdersRepository ordersRepository;
    private final AccountsRepository accountsRepository;


    @Bean
    public Job trMigrationJob(Step trMigrationStep){
        return jobBuilderFactory.get("trMigrationJob")
                .incrementer(new RunIdIncrementer())
                .start(trMigrationStep)
                .build();
    }

    @Bean
    @JobScope
    public Step trMigrationStep(ItemReader trOrdersReader, ItemWriter trOrdersWriter){
        return stepBuilderFactory.get("trMigrationStep")
                .<Orders, Orders>chunk(5)
                .reader(trOrdersReader)
                .processor((ItemProcessor<Orders, Accounts>) item -> new Accounts(item))
                .writer(trOrdersWriter)
                .build();

    }

    @Bean
    @StepScope
    public RepositoryItemReader<Orders> trOrdersReader(){
        return new RepositoryItemReaderBuilder<Orders>()
                .name("trOrdersBuilder")
                .repository(ordersRepository)
                .methodName("findAll")
                .pageSize(5) //chunk 사이즈와 동일
                .arguments(Collections.emptyList())
                .sorts(Collections.singletonMap("id", Sort.Direction.ASC))
                .build();

    }

    @Bean
    @StepScope
    public RepositoryItemWriter<Accounts> trOrdersWriter(){
        return new RepositoryItemWriterBuilder<Accounts>()
                .repository(accountsRepository)
                .methodName("save")
                .build();

    }

}
