INSERT INTO public.athorities(
	id, role)
	VALUES (1, 'ROLE_USER');

	INSERT INTO public.athorities(
	id, role)
	VALUES (2, 'ROLE_ADMIN');



	INSERT INTO public.users(
	id, email, enabled, name, password, authority_id)
	VALUES (1, 'root@mail.com', true, 'root', '$2a$10$aWlZ726biY80v.HlZ5INiuOwhOQSsWvBDY2Cz5l4RHNkymy0UiQEC', 2);

    --пароль 123