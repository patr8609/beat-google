

import java.io.IOException;
import java.util.ArrayList;

public class WebTree {
	public WebNode root;
	public ArrayList<WebPage> webList; // ���雯���靘�

	public WebTree(ArrayList<Keyword> keywords) throws IOException {
		webList = new ArrayList<>();

		for (int i = 0; i < HtmlMatcher.web.size(); i++) {
			webList.add(HtmlMatcher.web.get(i));
			this.root = new WebNode(HtmlMatcher.web.get(i));
			setAllScore(root, keywords);
		}
	}

	private void setAllScore(WebNode startNode, ArrayList<Keyword> keywords) throws IOException {
		startNode.setNodeScore(keywords);
	}

	public String[][] sort() {
		// �doQuicksort����
		webList = doQuickSort(webList);

		// list the result
		String[][] s = new String[webList.size()][4];
		for (int i = 0; i < webList.size() && i < 15; i++) {
			String name = webList.get(i).name;
			String url = webList.get(i).url;
			int k = i + 1;
			s[i][0] = k + ". " + name;
			s[i][1] = url;
			s[i][2] = "[Subpages]";
		}
		return s;
	}

	private ArrayList<WebPage> doQuickSort(ArrayList<WebPage> list) {
		if (list.size() < 2) {
			return list;
		}

		ArrayList<WebPage> result = new ArrayList<>();
		ArrayList<WebPage> lessList = new ArrayList<>();
		ArrayList<WebPage> equalList = new ArrayList<>();
		ArrayList<WebPage> greatList = new ArrayList<>();
		int pivotIndex = list.size() / 2;
		WebPage pivotPage = list.get(pivotIndex);

		for (int i = 0; i < list.size(); i++) {
			WebPage page = list.get(i);
			if (page.score > pivotPage.score) {
				greatList.add(page);
			} else if (page.score < pivotPage.score) {
				lessList.add(page);
			} else {
				equalList.add(page);
			}
		}

		result.addAll(doQuickSort(greatList));
		result.addAll(equalList);
		result.addAll(doQuickSort(lessList));
		return result;
	}
}