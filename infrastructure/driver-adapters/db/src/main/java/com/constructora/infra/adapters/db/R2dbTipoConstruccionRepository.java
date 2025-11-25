package com.constructora.infra.adapters.db;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface R2dbTipoConstruccionRepository extends R2dbcRepository<TipoConstruccionEntity, Long> {
}
