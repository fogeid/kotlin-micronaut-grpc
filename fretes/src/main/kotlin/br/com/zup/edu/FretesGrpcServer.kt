package br.com.zup.edu

import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.grpc.stub.StreamObserver
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import java.lang.IllegalArgumentException
import kotlin.random.Random

@Singleton
class FretesGrpcServer: FretesServiceGrpc.FretesServiceImplBase() {
    private val logger = LoggerFactory.getLogger(FretesServiceGrpc::class.java)
    override fun calculaFrete(request: CalculaFreteRequest?, responseObserver: StreamObserver<CalculaFreteResponse>?) {
        logger.info("Calculando frete para request: $request")
        
        val valor = Random.nextDouble(from = 0.0, until = 140.0)
        val response = CalculaFreteResponse.newBuilder()
            .setCep(request!!.cep)
            .setValor(valor)
            .build()

        logger.info("Frete calculado: $response")

        responseObserver!!.onNext(response)
        responseObserver.onCompleted()
    }
}