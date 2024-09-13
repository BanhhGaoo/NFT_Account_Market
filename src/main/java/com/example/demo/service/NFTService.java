package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.NFT;

@Service
public class NFTService {
	public List<NFT> getNFTsFromWallet(String walletAddress) {
		String url = "https://api.moralis.io/v2/" + walletAddress + "/nft?chain=solana";
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(url, String.class);

		List<NFT> nftList = new ArrayList<>();
		JSONObject jsonObject = new JSONObject(response);
		JSONArray jsonArray = jsonObject.getJSONArray("result");

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject nftObject = jsonArray.getJSONObject(i);
			NFT nft = new NFT();
			nft.setMint(nftObject.getString("mint"));
			nft.setName(nftObject.getString("name"));
			nft.setUri(nftObject.getString("uri"));
			nft.setImageUrl(nftObject.getString("image"));
			nftList.add(nft);
		}

		return nftList;
	}
}
