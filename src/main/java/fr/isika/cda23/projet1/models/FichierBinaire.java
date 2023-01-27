package fr.isika.cda23.projet1.models;

import java.io.RandomAccessFile;

public class FichierBinaire {

	public static void creationFichier() {

		try (RandomAccessFile raf = new RandomAccessFile("STAGIAIRES.bin", "rw")) {
			Noeud racine = new Noeud(new Stagiaire("LACROIX", "Pascale", "91", "BOBI 5", "2008"));

			raf.writeChars(racine.getCle().nomLong());
			raf.writeChars(racine.getCle().prenomLong());
			raf.writeChars(racine.getCle().codePostalLong());
			raf.writeChars(racine.getCle().promotionLong());
			raf.writeChars(racine.getCle().getDate());
			raf.writeInt(racine.getFilsGauche());
			raf.writeInt(racine.getFilsDroit());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void ecritureFichier(Noeud noeud) {
		try (RandomAccessFile raf = new RandomAccessFile("STAGIAIRES.bin", "rw")) {
			raf.seek(raf.length());

			raf.writeChars(noeud.getCle().nomLong());
			raf.writeChars(noeud.getCle().prenomLong());
			raf.writeChars(noeud.getCle().codePostalLong());
			raf.writeChars(noeud.getCle().promotionLong());
			raf.writeChars(noeud.getCle().getDate());
			raf.writeInt(noeud.getFilsGauche());
			raf.writeInt(noeud.getFilsDroit());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void lireFichier(int index) {
		while (index < 100) {
			try {
				RandomAccessFile raf = new RandomAccessFile("STAGIAIRES.bin", "r");
				raf.seek(index * 126);
				String nomBin = "";
				String prenomBin = "";
				String codePostalBin = "";
				String promotionBin = "";
				String dateBin = "";
				int filsGaucheBin;
				int filsDroitBin;
				for (int i = 0; i < Stagiaire.TAILLE_NOM_MAX; i++) {
					nomBin += raf.readChar();
				}
				for (int i = 0; i < Stagiaire.TAILLE_PRENOM_MAX; i++) {
					prenomBin += raf.readChar();
				}
				for (int i = 0; i < Stagiaire.TAILLE_CODE_POSTAL_MAX; i++) {
					codePostalBin += raf.readChar();
				}
				for (int i = 0; i < Stagiaire.TAILLE_PROMO_MAX; i++) {
					promotionBin += raf.readChar();
				}
				for (int i = 0; i < 4; i++) {
					dateBin += raf.readChar();
				}
				filsGaucheBin = raf.readInt();
				filsDroitBin = raf.readInt();
				
				index += 1;

				System.out.println("nom : " + nomBin + " || prénom : " + prenomBin + " || cp : " + codePostalBin
						+ " || promotion : " + promotionBin + " || date : " + dateBin + " || FG : " + filsGaucheBin
						+ " || FD : " + filsDroitBin);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@SuppressWarnings("null")
	public static int lastIndex() {
		try (RandomAccessFile raf = new RandomAccessFile("STAGIAIRES.bin", "rw")) {
			return (int) raf.length() / 126;

		} catch (Exception e) {
			e.printStackTrace();
			return (Integer) null;
		}
	}
}
