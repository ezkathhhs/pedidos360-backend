package pedidos360.backend.consumer; // Ajusta el paquete según tu proyecto

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class ProductConsumer {

    // Si tienes un ProductService para la BD, inyéctalo aquí en el futuro.

    @RabbitListener(queues = "${app.queue.inventory}")
    public void processInventory(String orderDetails, Channel channel, Message message) throws IOException {
        try {
            System.out.println("📦 Orden recibida en Productos. Descontando stock para: " + orderDetails);
            
            // Aquí iría tu lógica real de base de datos (ej: productService.descontarStock(id, cantidad))
            System.out.println("✅ Simulación: Stock descontado exitosamente en la base de datos.");
            
            // Confirmación exitosa (ACK)
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            System.err.println("❌ Error al descontar el inventario.");
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }
}