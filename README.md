# orders-management

Sistema de gestão de pedidos

## Especificações técnicas

Arquitetura utilizada: Hexagonal

- Java 21
- SpringBoot 3.2.0
- postgresql
- RabbitMQ

# Recursos

#### [POST /order/create] - Criar Pedidos
```javascript
curl --request POST \
  --url http://localhost:8080/order/create \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/10.3.1' \
  --data '[
	{
		"date": "2025-02-13 02:31:37",
		"code": "47",
		"products": [
			{
				"code": "1",
				"description": "Manga",
				"price": "10.2"
			}
		]
	}
]
'
```

#### [GET /order] - Busca de Pedidos paginados 
```javascript
curl --request GET \
  --url http://localhost:8080/order \
  --header 'User-Agent: insomnia/10.3.1'
```

