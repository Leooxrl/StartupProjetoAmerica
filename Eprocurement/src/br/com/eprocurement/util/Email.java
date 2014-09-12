package br.com.eprocurement.util;

import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

	Properties props = new Properties();
	Session session;
	private static final String REMETENTE = "leandro.eprocurement@gmail.com";
	private static final String SENHA = "tccruonleandro";

	private void configurarPropriedades() {

		/** Parâmetros de conexão com servidor Gmail */
		this.props.put("mail.smtp.host", "smtp.gmail.com");
		this.props.put("mail.smtp.socketFactory.port", "465");
		this.props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		this.props.put("mail.smtp.auth", "true");
		this.props.put("mail.smtp.port", "465");

	}

	private void configurarAutenticacao() {

		this.session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(REMETENTE, SENHA);
					}
				});

		/** Ativa Debug para sessão */
		this.session.setDebug(true);

	}

	public void enviarEmail(List<String> listaDestinatarios, String assunto,
			String mensagem) {

		this.configurarPropriedades();
		this.configurarAutenticacao();

		try {

			for (String destinatario : listaDestinatarios) {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(REMETENTE)); // Remetente

				Address[] toUser = InternetAddress // Destinatário(s)
						// .parse("leonardo.rox1@gmail.com, seucolega@hotmail.com, seuparente@yahoo.com.br");
						.parse(destinatario);

				message.setRecipients(Message.RecipientType.TO, toUser);
				message.setSubject(assunto);// Assunto
				message.setText(mensagem);
				/** Método para enviar a mensagem criada */
				Transport.send(message);

			}

			System.out.println("Feito!!!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
