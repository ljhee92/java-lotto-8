package lotto;

import java.util.List;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoMachine lottoMachine = new LottoMachine();

        try {
            int purchaseAmount = readPurchaseAmount(inputView);
            List<Lotto> lottos = lottoMachine.generateLottos(purchaseAmount);
            outputView.printLottos(lottos);

            WinningNumbers winningNumbers = readWinningNumbers(inputView);
            LottoGame game = new LottoGame(lottos, purchaseAmount);

            Map<LottoResult, Integer> results = game.checkWinnings(winningNumbers);
            double returnRate = game.calculateReturnRate(results);
            outputView.printResults(results, returnRate);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int readPurchaseAmount(InputView inputView) {
        while (true) {
            try {
                return inputView.readPurchaseAmount();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static WinningNumbers readWinningNumbers(InputView inputView) {
        while (true) {
            try {
                List<Integer> winningNumberList = inputView.readWinningNumbers();
                int bonusNumber = inputView.readBonusNumber();
                return new WinningNumbers(winningNumberList, bonusNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
